package com.example.starwars.ui.home.sortFilter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.ExperimentalPagingApi
import com.example.starwars.databinding.SortFilterDialogBinding
import com.example.starwars.models.SortFilter
import com.example.starwars.ui.home.HomeFragment
import com.example.starwars.ui.home.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

private const val TAG = "BottomListDialogFragment"

@ExperimentalPagingApi
class BottomListDialogFragment(): BottomSheetDialogFragment() {

    private var _binding: SortFilterDialogBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SortFilterDialogBinding.inflate(inflater)

        when(tag){
            HomeFragment.SORT -> addSortAdapter()
            HomeFragment.FILTER -> addFilterAdapter()
        }
        return binding.root
    }

    private fun addSortAdapter() {
        val adapter = SortFilterAdapter(SortFilterClickListener {
            Toast.makeText(context,"This Feature will be coming soon!",Toast.LENGTH_SHORT).show()
            dismiss()
        })
        binding.sortFilterRecyclerView.adapter = adapter
        val sortList = listOf<SortFilter>(SortFilter(1,"Name",false),
            SortFilter(2,"Created",false), SortFilter(3,"Updated",false)
        )

        adapter.submitList(sortList)
    }

    private fun addFilterAdapter() {
        val adapter = SortFilterAdapter(SortFilterClickListener {
            Toast.makeText(context,"This Feature will be coming soon!",Toast.LENGTH_SHORT).show()
            dismiss()
        })
        binding.sortFilterRecyclerView.adapter = adapter
        val filterList = listOf<SortFilter>(SortFilter(1,"Male",false),
            SortFilter(2,"Female",false)
        )

        adapter.submitList(filterList)
    }

}