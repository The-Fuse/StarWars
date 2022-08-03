package com.example.starwars.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.database.LocalDataSource
import com.example.starwars.databinding.FragmentHomeBinding
import com.example.starwars.models.Character
import com.example.starwars.network.ApiService
import com.example.starwars.network.RemoteDataSource
import com.example.starwars.paging.LoaderAdapter
import com.example.starwars.repository.CharactersRepository

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: CharacterPagingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)

        val
        val localDataSource = LocalDataSource(dao)
        val apiService = ApiService
        val remoteDataSource = RemoteDataSource(apiService)
        val repository = CharactersRepository(localDataSource,remoteDataSource)
        viewModel = ViewModelProvider(this,HomeViewModelFactory(repository ))[HomeViewModel::class.java]


        initializeCharacters()

        return binding.root
    }

    private val data = mutableListOf<Character>(
        Character(
            1,
            listOf(
                "https://swapi.dev/api/films/1/",
                "https://swapi.dev/api/films/2/",
                "https://swapi.dev/api/films/3/",
                "https://swapi.dev/api/films/6/"
            ),
            "https://swapi.dev/api/planets/1/",
            "male",
            "fair",
            "2014-12-20T21:17:56.891000Z",
            "2014-12-09T13:50:51.644000Z",
            "77",
            listOf("https://swapi.dev/api/vehicles/14/", "https://swapi.dev/api/vehicles/30/"),
            "https://swapi.dev/api/people/1/",
            "blond",
            "19BBY",
            "blue",
            listOf(
                "https://swapi.dev/api/starships/12/",
                "https://swapi.dev/api/starships/22/"
            ),
            "Luke Skywalker",
            "172"
        ),
        Character(
            2,
            listOf(
                "https://swapi.dev/api/films/1/",
                "https://swapi.dev/api/films/2/",
                "https://swapi.dev/api/films/3/",
                "https://swapi.dev/api/films/6/"
            ),
            "https://swapi.dev/api/planets/1/",
            "male",
            "fair",
            "2014-12-20T21:17:56.891000Z",
            "2014-12-09T13:50:51.644000Z",
            "77",
            listOf("https://swapi.dev/api/vehicles/14/", "https://swapi.dev/api/vehicles/30/"),
            "https://swapi.dev/api/people/1/",
            "blond",
            "19BBY",
            "blue",
            listOf(
                "https://swapi.dev/api/starships/12/",
                "https://swapi.dev/api/starships/22/"
            ),
            "Mass Skywalker",
            "172"
        ),Character(
            3,
            listOf(
                "https://swapi.dev/api/films/1/",
                "https://swapi.dev/api/films/2/",
                "https://swapi.dev/api/films/3/",
                "https://swapi.dev/api/films/6/"
            ),
            "https://swapi.dev/api/planets/1/",
            "male",
            "fair",
            "2014-12-20T21:17:56.891000Z",
            "2014-12-09T13:50:51.644000Z",
            "77",
            listOf("https://swapi.dev/api/vehicles/14/", "https://swapi.dev/api/vehicles/30/"),
            "https://swapi.dev/api/people/1/",
            "blond",
            "19BBY",
            "blue",
            listOf(
                "https://swapi.dev/api/starships/12/",
                "https://swapi.dev/api/starships/22/"
            ),
            "Lukde Skywalker",
            "172"
        )
    )

    private fun initializeCharacters() {

        adapter = CharacterPagingAdapter()
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.charactersRecyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )

        binding.charactersRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
        viewModel.charactersList.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle,it)
        }
    }
}