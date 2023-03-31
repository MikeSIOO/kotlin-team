package com.example.kotkin_team.storage.presentation.product

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotkin_team.databinding.ItemStorageProductBinding
import com.example.kotkin_team.storage.domain.model.StorageProduct

class StorageProductHolder(private val binding: ItemStorageProductBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        storageProduct: StorageProduct,
        callback: (storageProduct: StorageProduct) -> Unit
    ) {
        Glide
            .with(binding.root.context)
            .load(storageProduct.image)
            .into(binding.image)

        binding.textView.text = storageProduct.name

        if (storageProduct.selected) {
            binding.checkSelect.visibility = View.VISIBLE
            binding.check.visibility = View.GONE
        } else {
            binding.checkSelect.visibility = View.GONE
            binding.check.visibility = View.VISIBLE
        }

        binding.root.setOnClickListener { callback(storageProduct) }
    }
}
