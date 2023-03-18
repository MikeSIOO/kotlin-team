package com.example.kotkin_team.products.presentation.category

import androidx.recyclerview.widget.DiffUtil
import com.example.kotkin_team.products.domain.model.ProductsCategory

object ProductsCategoryDiffItemCallback : DiffUtil.ItemCallback<ProductsCategory>() {
    override fun areItemsTheSame(oldItem: ProductsCategory, newItem: ProductsCategory): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ProductsCategory, newItem: ProductsCategory): Boolean {
        return oldItem.id == newItem.id
    }
}