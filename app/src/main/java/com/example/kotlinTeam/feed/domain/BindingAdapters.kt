package com.example.kotlinTeam.feed.domain

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.kotlinTeam.common.data.dataSource.model.recipe.CuisineDto
import com.example.kotlinTeam.common.data.dataSource.model.recipe.IngredientOo

@BindingAdapter("ingredient_string")
fun TextView.setIngredientString(ingredient: IngredientOo) {
    this.text =
        if (ingredient.amount.isNullOrEmpty()) ingredient.product else "${ingredient.product} - ${ingredient.amount}"
}

@BindingAdapter("android:text")
fun TextView.setText(cuisines: List<CuisineDto>) {
    val resultText = cuisines.map { it.title }.joinToString(separator = ",")
    this.text = if (resultText != "") resultText else "-"
}
