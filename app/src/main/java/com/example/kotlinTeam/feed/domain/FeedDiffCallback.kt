package com.example.kotlinTeam.feed.domain

import androidx.recyclerview.widget.DiffUtil
import com.example.kotlinTeam.common.data.dataSource.model.recipe.RecipeOo

class FeedDiffCallback : DiffUtil.ItemCallback<RecipeOo>() {
    override fun areItemsTheSame(oldItem: RecipeOo, newItem: RecipeOo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RecipeOo, newItem: RecipeOo): Boolean {
        return oldItem == newItem
    }
}
