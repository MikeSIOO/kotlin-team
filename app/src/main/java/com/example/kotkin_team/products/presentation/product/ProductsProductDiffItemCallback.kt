package com.example.kotkin_team.products.presentation.product

import androidx.recyclerview.widget.DiffUtil
import com.example.kotkin_team.products.domain.model.ProductsProduct

object ProductsProductDiffItemCallback : DiffUtil.ItemCallback<ProductsProduct>() {
    override fun areItemsTheSame(oldItem: ProductsProduct, newItem: ProductsProduct): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ProductsProduct, newItem: ProductsProduct): Boolean {
        return oldItem.id == newItem.id
    }
}