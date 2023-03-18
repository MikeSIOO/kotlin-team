package com.example.kotkin_team.products.presentation.category

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotkin_team.R
import com.example.kotkin_team.products.domain.model.Category

class CategoryHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val context by lazy { view.context }
    private val image by lazy { view.findViewById<ImageView>(R.id.image) }
    private val text by lazy { view.findViewById<TextView>(R.id.text_view) }

    fun bind(category: Category, callback: (category: Category) -> Unit) {
        Glide
            .with(context)
            .load(category.image)
            .into(image)

        text.text = category.name

        view.setOnClickListener { callback(category) }

    }
}