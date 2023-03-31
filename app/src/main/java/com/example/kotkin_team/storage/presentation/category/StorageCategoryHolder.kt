package com.example.kotkin_team.storage.presentation.category

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotkin_team.databinding.ItemStorageCategoryBinding
import com.example.kotkin_team.storage.domain.model.StorageCategory

class StorageCategoryHolder(private val binding: ItemStorageCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        storageCategory: StorageCategory,
        callback: (storageCategory: StorageCategory) -> Unit
    ) {
        Glide
            .with(binding.root.context)
            .load(storageCategory.image)
            .into(binding.image)

        binding.textView.text = storageCategory.name

        binding.root.setOnClickListener { callback(storageCategory) }
    }
}