package com.example.starwars.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.starwars.R
import com.example.starwars.database.DatabaseDao
import com.example.starwars.database.LocalDatabase
import com.example.starwars.databinding.FragmentHomeBinding
import com.example.starwars.models.Character
import com.example.starwars.repository.CharactersRepository

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: CharactersRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)

        val dao = LocalDatabase.getDatabase(requireContext()).getDatabaseDao()

        val repository = CharactersRepository(dao)
        viewModel = ViewModelProvider(this,HomeViewModelFactory(repository ))[HomeViewModel::class.java]

        adapter = CharactersRecyclerAdapter()
        binding.charactersRecyclerView.adapter = adapter

        initializeCharacters(binding,adapter)

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

    private fun initializeCharacters(binding: FragmentHomeBinding,adapter: CharactersRecyclerAdapter) {
        viewModel.getCharacters().observe(viewLifecycleOwner,Observer{
            adapter.submitList(it.data)
            it.data?.let { it1 -> viewModel.insertCharacters(it1) }
        })
    }
}