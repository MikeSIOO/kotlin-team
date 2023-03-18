package com.example.kotkin_team.products.presentation.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.kotkin_team.R
import com.example.kotkin_team.products.domain.model.Category

class CategoryAdapter(private val callback: (recipe: Category) -> Unit) :
    ListAdapter<Category, CategoryHolder>(CategoryDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_products_category, parent, false)
        return CategoryHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        getItem(position).let { holder.bind(it, callback) }
    }
}