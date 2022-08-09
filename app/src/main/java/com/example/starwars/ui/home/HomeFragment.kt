package com.example.starwars.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.MainApplication
import com.example.starwars.database.LocalDataSource
import com.example.starwars.database.LocalDatabase
import com.example.starwars.databinding.FragmentHomeBinding
import com.example.starwars.models.Character
import com.example.starwars.network.ApiService
import com.example.starwars.network.RemoteDataSource
import com.example.starwars.paging.LoaderAdapter
import com.example.starwars.repository.CharactersRepository
import com.example.starwars.ui.home.sortFilter.BottomListDialogFragment
import retrofit2.Retrofit
import javax.inject.Inject

@ExperimentalPagingApi
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    private val TAG = "HomeFragment"
    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    private lateinit var adapter: CharacterPagingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        (activity?.application as MainApplication).applicationComponent.injectHome(this)

        viewModel = ViewModelProvider(this,homeViewModelFactory)[HomeViewModel::class.java]

        initializeCharacters()

        initializeClickListeners()

        return binding.root
    }

    private fun initializeClickListeners() {
        binding.sortButton.setOnClickListener {
            BottomListDialogFragment().show(childFragmentManager, SORT)
        }
        binding.filterButotn.setOnClickListener {
            BottomListDialogFragment().show(childFragmentManager, FILTER)
        }
    }

    private fun initializeCharacters() {

        adapter = CharacterPagingAdapter(CharacterClickListener {
            this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMoviesListFragment(it))
        })
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.charactersRecyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )

        binding.charactersRecyclerView.apply {
            setHasFixedSize(true)
        }

        viewModel.charactersList.observe(viewLifecycleOwner) {
            Log.d(TAG, "initializeCharacters: $it")
            adapter.submitData(lifecycle,it)
        }
    }

    companion object {
        const val SORT = "sort"
        const val FILTER = "filter"
    }
}