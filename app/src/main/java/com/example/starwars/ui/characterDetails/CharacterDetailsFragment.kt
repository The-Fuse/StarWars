package com.example.starwars.ui.characterDetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.paging.ExperimentalPagingApi
import com.example.starwars.MainApplication
import com.example.starwars.databinding.FragmentCharacterDetailsBinding
import com.example.starwars.repository.CharactersRepository
import com.example.starwars.utils.Result
import javax.inject.Inject

@ExperimentalPagingApi
class CharacterDetailsFragment : Fragment() {

    private val TAG = "CharacterDetailsFragment"
    private lateinit var adapter: CharacterFilmsRecyclerAdapter

    @Inject
    lateinit var repository: CharactersRepository
    private lateinit var viewModel: CharacterDetailsViewModel
    private lateinit var binding: FragmentCharacterDetailsBinding

    private val args: CharacterDetailsFragmentArgs by navArgs()
    

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailsBinding.inflate(inflater)

        (activity?.application as MainApplication).applicationComponent.injectFilmDetails(this)

        viewModel = ViewModelProvider(
            this,
            CharacterDetailsViewModelFactory(repository,args.characterDetails)
        )[CharacterDetailsViewModel::class.java]

        binding.character = args.characterDetails

        initializeAdapter()

        return binding.root
    }

    private fun initializeAdapter() {
        adapter = CharacterFilmsRecyclerAdapter()
        binding.filmRecyclerView.adapter = adapter

        viewModel.filmDetails.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "initializeAdapter: ${it.data}")
            adapter.submitList(it.data)
        })
    }


}