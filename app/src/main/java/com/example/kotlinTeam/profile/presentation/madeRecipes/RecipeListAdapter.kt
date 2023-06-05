package com.example.kotlinTeam.profile.presentation.madeRecipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.kotlinTeam.databinding.RecipeItemSmallBinding
import com.example.kotlinTeam.profile.domain.model.MadeRecipe

class RecipeListAdapter(
    private val callback: (madeRecipe: MadeRecipe) -> Unit
) : PagingDataAdapter<MadeRecipe, RecipeViewHolder>(RecipeDiffItemCallback) {

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        getItem(position).let { holder.bind(it!!, callback) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = RecipeItemSmallBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return RecipeViewHolder(dataBinding)
    }
}
