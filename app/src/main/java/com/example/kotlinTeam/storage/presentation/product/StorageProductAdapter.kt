package com.example.kotlinTeam.storage.presentation.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.kotlinTeam.databinding.ItemStorageProductBinding
import com.example.kotlinTeam.storage.domain.model.StorageProduct

class StorageProductAdapter(private val callback: (storageProduct: StorageProduct) -> Unit) :
    PagingDataAdapter<StorageProduct, StorageProductHolder>(StorageProductDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageProductHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemStorageProductBinding.inflate(layoutInflater, parent, false)
        return StorageProductHolder(binding)
    }

    override fun onBindViewHolder(holder: StorageProductHolder, position: Int) {
        getItem(position).let { holder.bind(it!!, callback) }
    }
}
