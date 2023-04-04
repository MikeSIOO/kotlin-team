package com.example.kotlinTeam.storage.presentation.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.kotlinTeam.databinding.ItemStorageCategoryBinding
import com.example.kotlinTeam.storage.domain.model.StorageCategory

class StorageCategoryAdapter(private val callback: (storageCategory: StorageCategory) -> Unit) :
    ListAdapter<StorageCategory, StorageCategoryHolder>(StorageCategoryDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageCategoryHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemStorageCategoryBinding.inflate(layoutInflater, parent, false)
        return StorageCategoryHolder(binding)
    }

    override fun onBindViewHolder(holder: StorageCategoryHolder, position: Int) {
        getItem(position).let { holder.bind(it, callback) }
    }
}
