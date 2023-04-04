package com.example.kotlinTeam.feed.domain

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.kotlinTeam.feed.data.Ingredient

@BindingAdapter("ingredient_string")
fun TextView.setIngredientString(ingredient: Ingredient) {
    this.text =
        if (ingredient.amount.isNullOrEmpty()) ingredient.product else "${ingredient.product} - ${ingredient.amount}"
}
