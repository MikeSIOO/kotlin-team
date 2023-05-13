package com.example.kotlinTeam.storage.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.kotlinTeam.databinding.ItemStorageCategoryBinding
import com.example.kotlinTeam.databinding.ItemStorageProductBinding
import com.example.kotlinTeam.storage.domain.model.StorageDataModel

class StorageHolder(private val binding: ViewBinding) : //TODO category
    RecyclerView.ViewHolder(binding.root) {

    private fun bindCategory(item: StorageDataModel.StorageCategory) {
        if (binding is ItemStorageCategoryBinding) {
            Glide
                .with(binding.root.context)
                .load(item.image)
                .into(binding.image)

            binding.textView.text = item.title
        }
    }

    private fun bindProduct(item: StorageDataModel.StorageProduct) {
        if (binding is ItemStorageProductBinding) {
            Glide
                .with(binding.root.context)
                .load(item.image)
                .into(binding.image)

            binding.textView.text = item.title

            if (item.selected) {
                binding.checkSelect.visibility = View.VISIBLE
                binding.check.visibility = View.GONE
            } else {
                binding.checkSelect.visibility = View.GONE
                binding.check.visibility = View.VISIBLE
            }
        }
    }

    fun bind(storageDataModel: StorageDataModel) {
        when (storageDataModel) {
            is StorageDataModel.StorageCategory -> bindCategory(storageDataModel)
            is StorageDataModel.StorageProduct -> bindProduct(storageDataModel)
        }
    }
}
