package com.example.starwars.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.starwars.models.Character
import com.example.starwars.network.IRemoteDataSource
import com.example.starwars.network.RemoteDataSource
import com.example.starwars.utils.Result

class CharacterPagingSource(private val remoteDataSource: IRemoteDataSource) :
    PagingSource<Int, Character>() {

    private val TAG = "CharacterPagingSource"
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val position = params.key ?: 1
        return try {
            val response = remoteDataSource.fetchCharacters(position)

            Log.d(TAG, "load: $response")

            if (response.status == Result.Status.SUCCESS && response.data != null) {
                Log.d(TAG, "load: ${response.data.results}")
                LoadResult.Page(
                    data = response.data.results,
                    prevKey = if (position == 1) null else (position - 1),
                    nextKey = if (response.data.next==null) null else (position + 1)
                )
            } else {
                LoadResult.Error(throw Exception("No Response"))
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}