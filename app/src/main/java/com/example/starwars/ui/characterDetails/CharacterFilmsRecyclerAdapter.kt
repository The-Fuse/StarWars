package com.example.starwars.ui.characterDetails

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.databinding.FilmItemBinding
import com.example.starwars.models.CharacterFilm

private val TAG = "CharacterFilmsRecyclerAdapter"

class CharacterFilmsRecyclerAdapter :
    ListAdapter<CharacterFilm, CharacterFilmsRecyclerAdapter.ViewHolder>(FilmsDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG, "onCreateViewHolder: Inside Viewholder")
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: Inside viewholder")
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(private val binding: FilmItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CharacterFilm) {
            Log.d(TAG, "bind: inside bind")
            binding.film = item
            Log.d(TAG, "bind: $item")
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FilmItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

}

class FilmsDiffCallback : DiffUtil.ItemCallback<CharacterFilm>() {
    override fun areItemsTheSame(oldItem: CharacterFilm, newItem: CharacterFilm): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: CharacterFilm, newItem: CharacterFilm): Boolean {
        return oldItem == newItem
    }
}