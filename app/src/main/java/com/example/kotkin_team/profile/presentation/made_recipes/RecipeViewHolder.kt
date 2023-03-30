package com.example.kotkin_team.profile.presentation.made_recipes

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kotkin_team.R
import com.example.kotkin_team.databinding.RecipeItemSmallBinding
import com.example.kotkin_team.profile.domain.model.MadeRecipe
import com.example.kotkin_team.profile.presentation.util.bindImage

class RecipeViewHolder(
    private val dataBinding: RecipeItemSmallBinding
) : RecyclerView.ViewHolder(dataBinding.root) {
    fun bind(madeRecipe: MadeRecipe, callback: (madeRecipe: MadeRecipe) -> Unit) {
        dataBinding.madeRecipeTitle.text = madeRecipe.title
        bindImage(dataBinding.root, madeRecipe.image ?: dataBinding.root.context.getString(R.string.image_template), dataBinding.recipeImageView)
        dataBinding.root.setOnClickListener {
            callback(madeRecipe)
            Toast.makeText(dataBinding.root.context, "You have selected ${madeRecipe.title}", Toast.LENGTH_SHORT).show()
        }
    }
}
