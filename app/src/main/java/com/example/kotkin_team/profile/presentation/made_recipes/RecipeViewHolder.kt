package com.example.kotkin_team.profile.presentation.made_recipes

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotkin_team.R
import com.example.kotkin_team.profile.domain.model.Recipe
import com.bumptech.glide.request.target.Target

class RecipeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val title by lazy { view.findViewById<TextView>(R.id.made_recipe_title) }
    private val image by lazy { view.findViewById<ImageView>(R.id.recipe_image_view) }

    fun bind(recipe: Recipe, callback: (recipe: Recipe) -> Unit) {
        title.text = recipe.title
        val url = recipe.image ?: "https://sovjen.ru/wp-content/uploads/2020/12/2krendelya.ru_.jpg"
        Glide.with(view)
            .load(url)
            .override(Target.SIZE_ORIGINAL)
            .into(image)
        image.setBackgroundColor(0xFF00FF00.toInt())

        view.setOnClickListener{ callback(recipe) }
    }
}