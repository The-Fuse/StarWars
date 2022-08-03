package com.example.starwars.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.starwars.models.Character
import com.example.starwars.repository.CharactersRepository
import com.example.starwars.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val charactersRepository: CharactersRepository): ViewModel() {

    val charactersList: LiveData<PagingData<Character>> = charactersRepository.getCharactersCount().cachedIn(viewModelScope)

}