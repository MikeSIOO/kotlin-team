package com.example.kotkin_team.profile.presentation.made_recipes.load_state

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.kotkin_team.databinding.LoadStateItemBinding

class MadeRecipesLoadStateViewHolder(
    private val dataBinding: LoadStateItemBinding
) : RecyclerView.ViewHolder(dataBinding.root) {

    fun bind(loadState: LoadState) {
        dataBinding.loadStateProgress.isVisible = loadState is LoadState.Loading
    }
}
