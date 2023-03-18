package com.example.kotkin_team.products.presentation.category

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotkin_team.R
import com.example.kotkin_team.products.domain.model.ProductsCategory

class ProductsCategoryHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val context by lazy { view.context }
    private val image by lazy { view.findViewById<ImageView>(R.id.image) }
    private val text by lazy { view.findViewById<TextView>(R.id.text_view) }

    fun bind(productsCategory: ProductsCategory, callback: (productsCategory: ProductsCategory) -> Unit) {
        Glide
            .with(context)
            .load(productsCategory.image)
            .into(image)

        text.text = productsCategory.name

        view.setOnClickListener { callback(productsCategory) }

    }
}