package com.example.kotkin_team.storage.presentation.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.kotkin_team.R
import com.example.kotkin_team.storage.domain.model.StorageCategory

class StorageCategoryAdapter(private val callback: (storageCategory: StorageCategory) -> Unit) :
    ListAdapter<StorageCategory, StorageCategoryHolder>(StorageCategoryDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageCategoryHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_storage_category, parent, false)
        return StorageCategoryHolder(view)
    }

    override fun onBindViewHolder(holder: StorageCategoryHolder, position: Int) {
        getItem(position).let { holder.bind(it, callback) }
    }
}