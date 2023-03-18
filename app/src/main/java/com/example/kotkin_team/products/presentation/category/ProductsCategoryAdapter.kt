package com.example.kotkin_team.products.presentation.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.kotkin_team.R
import com.example.kotkin_team.products.domain.model.ProductsCategory

class ProductsCategoryAdapter(private val callback: (recipe: ProductsCategory) -> Unit) :
    ListAdapter<ProductsCategory, ProductsCategoryHolder>(ProductsCategoryDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsCategoryHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_products_category, parent, false)
        return ProductsCategoryHolder(view)
    }

    override fun onBindViewHolder(holder: ProductsCategoryHolder, position: Int) {
        getItem(position).let { holder.bind(it, callback) }
    }
}