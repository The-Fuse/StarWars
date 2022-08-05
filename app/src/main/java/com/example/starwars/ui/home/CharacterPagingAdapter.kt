package com.example.starwars.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.databinding.CharacterBinding
import com.example.starwars.models.Character

class CharacterPagingAdapter(private val characterClickListener: CharacterClickListener): PagingDataAdapter<Character, CharacterPagingAdapter.ViewHolder>(
    CharacterDiffCallback()
) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item,characterClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: CharacterBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Character,characterClickListener: CharacterClickListener){
            binding.apply {
                character = item
                clickListener = characterClickListener
                executePendingBindings()
            }
        }

        companion object{
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CharacterBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }


}

class CharacterDiffCallback: DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem ==  newItem
    }
}

class CharacterClickListener(val clickListener: (character: Character)-> Unit) {
    fun onClick(character: Character) = clickListener(character)
}