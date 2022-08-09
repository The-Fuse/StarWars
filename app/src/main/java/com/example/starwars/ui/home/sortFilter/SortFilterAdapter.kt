package com.example.starwars.ui.home.sortFilter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.DifferCallback
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.databinding.SortFilterDialogItemBinding
import com.example.starwars.models.SortFilter

class SortFilterAdapter(private val clickListener: SortFilterClickListener): ListAdapter<SortFilter,SortFilterAdapter.ViewHolder>(SortFilterDiffUtilCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position),clickListener)
    }

    class ViewHolder private constructor(private val binding: SortFilterDialogItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SortFilter, sortFilterClickListener: SortFilterClickListener) {
            binding.sortFilter = item
            binding.clickListener = sortFilterClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SortFilterDialogItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}

class SortFilterDiffUtilCallBack: DiffUtil.ItemCallback<SortFilter>() {
    override fun areItemsTheSame(oldItem: SortFilter, newItem: SortFilter): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SortFilter, newItem: SortFilter): Boolean {
        return oldItem==newItem
    }

}

class SortFilterClickListener(val clickListener: (sortFilter: SortFilter) -> Unit) {
    fun onClick(sortFilter: SortFilter) = clickListener(sortFilter)
}