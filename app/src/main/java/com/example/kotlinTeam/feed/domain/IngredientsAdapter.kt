package com.example.kotlinTeam.feed.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinTeam.databinding.IngredientItemBinding
import com.example.kotlinTeam.common.data.dataSource.model.recipe.IngredientOo

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {
    class IngredientsViewHolder(private val binding: IngredientItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ingredient: IngredientOo) {
            binding.ingredientTv.setIngredientString(ingredient)
        }
    }

    private var ingredients: List<IngredientOo> = listOf()
    fun setData(data: List<IngredientOo>) {
        ingredients = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientsViewHolder {
        return IngredientsViewHolder(
            IngredientItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }

    override fun getItemCount(): Int = ingredients.size
}
