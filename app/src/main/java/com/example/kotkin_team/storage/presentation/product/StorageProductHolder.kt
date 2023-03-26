package com.example.kotkin_team.storage.presentation.product

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotkin_team.R
import com.example.kotkin_team.storage.domain.model.StorageProduct

class StorageProductHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val context by lazy { view.context }
    private val image by lazy { view.findViewById<ImageView>(R.id.image) }
    private val text by lazy { view.findViewById<TextView>(R.id.text_view) }
    private val background by lazy { view.findViewById<LinearLayout>(R.id.item_storage_product) }

    fun bind(
        storageProduct: StorageProduct,
        callback: (storageProduct: StorageProduct) -> Unit
    ) {
        Glide
            .with(context)
            .load(storageProduct.image)
            .into(image)

        text.text = storageProduct.name

        if (storageProduct.selected)
            background.setBackgroundColor(Color.RED)
        else
            background.setBackgroundColor(Color.GRAY)

        view.setOnClickListener { callback(storageProduct) }
    }
}