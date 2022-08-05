package com.example.starwars.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.starwars.repository.CharactersRepository
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor( private val charactersRepository: CharactersRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(charactersRepository) as T
    }

}