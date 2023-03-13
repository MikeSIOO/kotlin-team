package com.example.kotkin_team.profile.presentation.made_recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.kotkin_team.R
import com.example.kotkin_team.profile.domain.model.Recipe

class RecipeListAdapter(private val callback: (recipe: Recipe) -> Unit) : ListAdapter<Recipe, RecipeViewHolder>(RecipeDiffItemCallback) {

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        getItem(position).let { holder.bind(it, callback) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item_small, parent, false)
        return RecipeViewHolder(view)
    }
}