package com.example.kotkin_team.profile.presentation.made_recipes

import androidx.recyclerview.widget.DiffUtil
import com.example.kotkin_team.profile.domain.model.Recipe

object RecipeDiffItemCallback : DiffUtil.ItemCallback<Recipe>() {

    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.id == newItem.id
    }
}