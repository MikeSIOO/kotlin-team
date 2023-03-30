package com.example.kotkin_team.storage.presentation.product

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotkin_team.R
import com.example.kotkin_team.storage.domain.model.StorageProduct

class StorageProductHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val context by lazy { view.context }
    private val image by lazy { view.findViewById<ImageView>(R.id.image) }
    private val text by lazy { view.findViewById<TextView>(R.id.text_view) }
    private val check by lazy { view.findViewById<ImageView>(R.id.check) }
    private val checkSelect by lazy { view.findViewById<ImageView>(R.id.check_select) }

    fun bind(
        storageProduct: StorageProduct,
        callback: (storageProduct: StorageProduct) -> Unit
    ) {
        Glide
            .with(context)
            .load(storageProduct.image)
            .into(image)

        text.text = storageProduct.name

        if (storageProduct.selected) {
            checkSelect.visibility = View.VISIBLE
            check.visibility = View.GONE
        } else {
            checkSelect.visibility = View.GONE
            check.visibility = View.VISIBLE
        }

        view.setOnClickListener { callback(storageProduct) }
    }
}