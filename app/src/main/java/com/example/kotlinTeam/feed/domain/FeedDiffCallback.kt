package com.example.kotlinTeam.feed.domain

import androidx.recyclerview.widget.DiffUtil
import com.example.kotlinTeam.feed.data.Recipe

class FeedDiffCallback : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }
}
