package com.example.kotkin_team.products.presentation.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.kotkin_team.R
import com.example.kotkin_team.products.domain.model.ProductsProduct

class ProductsProductAdapter(private val callback: (recipe: ProductsProduct) -> Unit) :
    ListAdapter<ProductsProduct, ProductsProductHolder>(ProductsProductDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsProductHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_products_product, parent, false)
        return ProductsProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductsProductHolder, position: Int) {
        getItem(position).let { holder.bind(it, callback) }
    }
}