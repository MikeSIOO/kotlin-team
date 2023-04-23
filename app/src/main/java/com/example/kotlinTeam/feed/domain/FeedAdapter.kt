package com.example.kotlinTeam.feed.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinTeam.common.data.dataSource.model.recipe.RecipeOo
import com.example.kotlinTeam.databinding.RecipeCardBinding

class FeedAdapter : PagingDataAdapter<RecipeOo, FeedAdapter.ViewHolder>(FeedDiffCallback()) {

    class ViewHolder(private val binding: RecipeCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipeOo) {
            binding.recipe = recipe
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecipeCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = getItem(position)
        recipe.let { holder.bind(it!!) }
    }
}
