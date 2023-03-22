package com.example.kotkin_team.storage.presentation.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.kotkin_team.R
import com.example.kotkin_team.storage.domain.model.StorageProduct

class StorageProductAdapter(private val callback: (storageProduct: StorageProduct) -> Unit) :
    ListAdapter<StorageProduct, StorageProductHolder>(StorageProductDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageProductHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_storage_product, parent, false)
        return StorageProductHolder(view)
    }

    override fun onBindViewHolder(holder: StorageProductHolder, position: Int) {
        getItem(position).let { holder.bind(it, callback) }
    }
}