package com.example.kotlinTeam.storage.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.kotlinTeam.storage.domain.model.StorageDataModel

object StorageDiffItemCallback : DiffUtil.ItemCallback<StorageDataModel>() {
    override fun areItemsTheSame(oldItem: StorageDataModel, newItem: StorageDataModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: StorageDataModel, newItem: StorageDataModel): Boolean {
        return if (oldItem is StorageDataModel.StorageCategory && newItem is StorageDataModel.StorageCategory) {
            oldItem.id == newItem.id
        } else if (oldItem is StorageDataModel.StorageProduct && newItem is StorageDataModel.StorageProduct) {
            oldItem.id == newItem.id
        } else {
            false
        }
    }
}
