package com.example.kotlinTeam.profile.presentation.madeRecipes

import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinTeam.R
import com.example.kotlinTeam.databinding.RecipeItemSmallBinding
import com.example.kotlinTeam.profile.domain.model.MadeRecipe
import com.example.kotlinTeam.profile.presentation.util.bindImage

class RecipeViewHolder(
    private val dataBinding: RecipeItemSmallBinding
) : RecyclerView.ViewHolder(dataBinding.root) {
    fun bind(madeRecipe: MadeRecipe, callback: (madeRecipe: MadeRecipe) -> Unit) {
        dataBinding.madeRecipeTitle.text = madeRecipe.title
        bindImage(
            dataBinding.root,
            madeRecipe.image ?: dataBinding.root.context.getString(R.string.image_template),
            dataBinding.recipeImageView
        )
        dataBinding.root.setOnClickListener {
            callback(madeRecipe)
        }
    }
}
