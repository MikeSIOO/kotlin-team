package com.example.kotlinTeam.profile.presentation.madeRecipes

import androidx.recyclerview.widget.DiffUtil
import com.example.kotlinTeam.profile.domain.model.MadeRecipe

object RecipeDiffItemCallback : DiffUtil.ItemCallback<MadeRecipe>() {

    override fun areItemsTheSame(oldItem: MadeRecipe, newItem: MadeRecipe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MadeRecipe, newItem: MadeRecipe): Boolean {
        return oldItem == newItem
    }
}
