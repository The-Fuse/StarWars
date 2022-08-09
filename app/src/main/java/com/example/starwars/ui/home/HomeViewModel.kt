package com.example.starwars.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.starwars.models.Character
import com.example.starwars.repository.CharactersRepository
import com.example.starwars.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
class HomeViewModel  @Inject constructor(private val charactersRepository: CharactersRepository): ViewModel() {

    val charactersList: LiveData<PagingData<Character>> = charactersRepository.getCharactersCount()
        .cachedIn(viewModelScope)

}