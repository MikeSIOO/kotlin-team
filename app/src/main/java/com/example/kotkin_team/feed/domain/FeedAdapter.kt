package com.example.kotkin_team.feed.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotkin_team.databinding.RecipeCardBinding
import com.example.kotkin_team.feed.data.Recipe

class FeedAdapter : ListAdapter<Recipe, FeedAdapter.ViewHolder>(FeedDiffCallback()) {

    class ViewHolder(val binding: RecipeCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecipeCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.binding.recipe = recipe
    }
}
