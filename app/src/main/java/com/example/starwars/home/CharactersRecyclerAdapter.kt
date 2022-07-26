package com.example.starwars.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.databinding.CharacterBinding
import com.example.starwars.models.Character

class CharactersRecyclerAdapter() :ListAdapter<Character,CharactersRecyclerAdapter.ViewHolder>(CharacterDiffCallback()) {

    class ViewHolder private constructor(private val binding: CharacterBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Character){
            binding.characterName.text = item.name
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup):ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CharacterBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class CharacterDiffCallback: DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem ==  newItem
    }
}