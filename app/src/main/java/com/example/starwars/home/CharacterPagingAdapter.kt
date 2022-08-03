package com.example.starwars.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.databinding.CharacterBinding
import com.example.starwars.models.Character

class CharacterPagingAdapter: PagingDataAdapter<Character,CharacterPagingAdapter.ViewHolder>(CharacterDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent  )
    }

    class ViewHolder private constructor(private val binding: CharacterBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Character){
            binding.apply {
                character = item
                executePendingBindings()
            }
        }

        companion object{
            fun from(parent: ViewGroup):ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CharacterBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }


}