package com.example.kotlinTeam.profile.presentation.madeRecipes.loadState

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.kotlinTeam.databinding.LoadStateItemBinding

class MadeRecipesLoadStateAdapter : LoadStateAdapter<MadeRecipesLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: MadeRecipesLoadStateViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MadeRecipesLoadStateViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = LoadStateItemBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return MadeRecipesLoadStateViewHolder(dataBinding)
    }
}
