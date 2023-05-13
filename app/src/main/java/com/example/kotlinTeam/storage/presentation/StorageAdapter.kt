package com.example.kotlinTeam.storage.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.kotlinTeam.databinding.ItemStorageCategoryBinding
import com.example.kotlinTeam.databinding.ItemStorageProductBinding
import com.example.kotlinTeam.storage.domain.model.StorageDataModel

class StorageAdapter(private val callback: (item: StorageDataModel) -> Unit) :
    PagingDataAdapter<StorageDataModel, StorageHolder>(StorageDiffItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = when (viewType) {
            TYPE_CATEGORY -> ItemStorageCategoryBinding.inflate(layoutInflater, parent, false)
            TYPE_PRODUCT -> ItemStorageProductBinding.inflate(layoutInflater, parent, false)
            else -> throw IllegalArgumentException("Invalid type")
        }

        return StorageHolder(binding)
    }

    override fun onBindViewHolder(holder: StorageHolder, position: Int) {
        getItem(position).let { holder.bind(it!!, callback) }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is StorageDataModel.StorageCategory -> TYPE_CATEGORY
            else -> TYPE_PRODUCT
        }
    }

    companion object {
        private const val TYPE_CATEGORY = 0
        private const val TYPE_PRODUCT = 1
    }

}
