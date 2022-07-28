package com.example.starwars.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.models.Character
import com.example.starwars.repository.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val charactersRepository: CharactersRepository): ViewModel() {

    fun  getCharacters(): LiveData<List<Character>>{
        return charactersRepository.getCharacters()
    }

    fun insertCharacters(character: List<Character>){
        viewModelScope.launch(Dispatchers.IO) {
            charactersRepository.insertCharacters(character )
        }
    }
}