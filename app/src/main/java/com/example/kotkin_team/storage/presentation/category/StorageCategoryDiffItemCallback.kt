package com.example.kotkin_team.storage.presentation.category

import androidx.recyclerview.widget.DiffUtil
import com.example.kotkin_team.storage.domain.model.StorageCategory

object StorageCategoryDiffItemCallback : DiffUtil.ItemCallback<StorageCategory>() {
    override fun areItemsTheSame(oldItem: StorageCategory, newItem: StorageCategory): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: StorageCategory, newItem: StorageCategory): Boolean {
        return oldItem.id == newItem.id
    }
}
