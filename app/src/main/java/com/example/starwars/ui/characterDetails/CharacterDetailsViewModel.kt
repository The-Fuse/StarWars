package com.example.starwars.ui.characterDetails

import android.util.Log
import androidx.lifecycle.*
import com.example.starwars.models.Character
import com.example.starwars.models.CharacterFilm
import com.example.starwars.repository.CharactersRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.xml.transform.Result

class CharacterDetailsViewModel (private val repository: CharactersRepository, val character: Character) : ViewModel() {

    private val TAG = "CharacterDetailsViewModel"
    private val _filmDetails =  MutableLiveData<com.example.starwars.utils.Result<List<CharacterFilm>>>()
    val filmDetails: LiveData<com.example.starwars.utils.Result<List<CharacterFilm>>>
        get() = _filmDetails

    init {
        getFilmDetail()
    }
    private fun getFilmDetail() {
        val films = character.films
        val idList: MutableList<Int> = mutableListOf()
        for (i in films.indices){
            val film = films[i]
            idList.add(film[film.length-2].digitToInt())
        }
        val filmDetailList : MutableList<CharacterFilm> = mutableListOf()
        viewModelScope.launch {
            for (i in idList.indices){
                val film = repository.filmDetail(idList[i])
                film.data?.let { filmDetailList.add(it) }
            }
            _filmDetails.value = com.example.starwars.utils.Result(com.example.starwars.utils.Result.Status.SUCCESS,filmDetailList,"Nothing")
            Log.d(TAG, "getFilmDetail: ${_filmDetails.value}")
        }

    }
}

class CharacterDetailsViewModelFactory(private val repository: CharactersRepository, private val character: Character):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterDetailsViewModel::class.java)) {
            return CharacterDetailsViewModel(repository, character) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}