package com.example.starwars.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.starwars.repository.CharactersRepository

class HomeViewModelFactory( private val charactersRepository: CharactersRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(charactersRepository) as T
    }

}