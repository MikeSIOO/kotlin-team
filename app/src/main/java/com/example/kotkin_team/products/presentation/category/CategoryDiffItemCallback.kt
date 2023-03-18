package com.example.kotkin_team.products.presentation.category

import androidx.recyclerview.widget.DiffUtil
import com.example.kotkin_team.products.domain.model.Category

object CategoryDiffItemCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }
}