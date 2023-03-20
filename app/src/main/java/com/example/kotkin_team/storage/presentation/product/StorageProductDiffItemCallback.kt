package com.example.kotkin_team.storage.presentation.product

import androidx.recyclerview.widget.DiffUtil
import com.example.kotkin_team.storage.domain.model.StorageProduct

object StorageProductDiffItemCallback : DiffUtil.ItemCallback<StorageProduct>() {
    override fun areItemsTheSame(oldItem: StorageProduct, newItem: StorageProduct): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: StorageProduct, newItem: StorageProduct): Boolean {
        return oldItem.id == newItem.id
    }
}