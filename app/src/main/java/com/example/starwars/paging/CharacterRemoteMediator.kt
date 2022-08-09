package com.example.starwars.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.starwars.database.LocalDatabase
import com.example.starwars.models.Character
import com.example.starwars.models.CharacterRemoteKeys
import com.example.starwars.network.ApiService
import javax.inject.Inject

@ExperimentalPagingApi
class CharacterRemoteMediator @Inject constructor(
    private val apiService: ApiService,
    private val localDatabase: LocalDatabase
): RemoteMediator<Int,Character>()   {

    private val characterDao = localDatabase.getDatabaseDao()
    private val remoteKeysDao = localDatabase.getRemoteKeysDao()
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): MediatorResult {
        // Fetch Characters from api
        // Save characters + remote keys in db
        // Logic for - REFRESH, APPEND, PREPEND

        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevKey = remoteKeys?.prevKey
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevKey
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextKey
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }


            val response = apiService.getCharacters(currentPage)
            val endOfPaginationReached = response.body()?.count == currentPage

            val prevPage = if(currentPage==1) null else currentPage-1
            val nextPage = if (endOfPaginationReached) null else currentPage+1

            localDatabase.withTransaction {

                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    remoteKeysDao.deleteRemoteKeys()
                    characterDao.deleteCharacters()
                }

                characterDao.insertCharacters(response.body()!!.results)
                val keys = response.body()!!.results.map { character ->
                    CharacterRemoteKeys(
                        id = character.url,
                        prevKey =  prevPage,
                        nextKey = nextPage
                     )
                }
                remoteKeysDao.addRemoteKeys(keys)
            }
            MediatorResult.Success(endOfPaginationReached)

        }catch (e: Exception){
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Character>
    ): CharacterRemoteKeys? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.url?.let { id ->
                remoteKeysDao.getRemoteKeys(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Character>): CharacterRemoteKeys? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                // Get the remote keys of the first items retrieved
                remoteKeysDao.getRemoteKeys(character.url)
            }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Character>): CharacterRemoteKeys? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                // Get the remote keys of the last item retrieved
                remoteKeysDao.getRemoteKeys(id = character.url)
            }
    }
}