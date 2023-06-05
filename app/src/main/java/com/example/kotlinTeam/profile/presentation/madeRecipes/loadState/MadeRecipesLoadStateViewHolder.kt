package com.example.kotlinTeam.profile.presentation.madeRecipes.loadState

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinTeam.databinding.LoadStateItemBinding

class MadeRecipesLoadStateViewHolder(
    private val dataBinding: LoadStateItemBinding
) : RecyclerView.ViewHolder(dataBinding.root) {

    fun bind(loadState: LoadState) {
        dataBinding.loadStateProgress.isVisible = loadState is LoadState.Loading
    }
}
