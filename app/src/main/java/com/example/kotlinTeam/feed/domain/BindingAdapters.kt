package com.example.kotlinTeam.feed.domain

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.kotlinTeam.R
import com.example.kotlinTeam.common.data.dataSource.model.recipe.CuisineDto
import com.example.kotlinTeam.common.data.dataSource.model.recipe.IngredientOo

@BindingAdapter("ingredient_string")
fun TextView.setIngredientString(ingredient: IngredientOo) {
    this.text =
        if (ingredient.amount.isNullOrEmpty()) ingredient.product
        else "${ingredient.product} - ${ingredient.amount}"
}

@BindingAdapter("android:text")
fun TextView.setText(cuisines: List<CuisineDto>) {
    val resultText = cuisines.map { it.title }.joinToString(separator = ",")
    this.text = if (resultText != "") resultText else "-"
}

@BindingAdapter("imageUrl")
fun ImageView.loadImage(imageUrl: String?) {
    val errorDrawable = ResourcesCompat.getDrawable(resources, R.drawable.feed_error, context.theme)
    val noImageDrawable = ResourcesCompat.getDrawable(resources, R.drawable.profile, context.theme)
    if (imageUrl?.isBlank() == true) this.setImageDrawable(noImageDrawable)
    else Glide.with(context).load(imageUrl).error(errorDrawable).into(this)
}
