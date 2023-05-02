package com.example.kotlinTeam.feed.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinTeam.databinding.FeedLoadStateBinding

class FeedLoadStateAdapter(): LoadStateAdapter<FeedLoadStateAdapter.Holder>() {

    class Holder(private val binding: FeedLoadStateBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.errorMsg.isVisible = loadState is LoadState.Error
        }
    }

    override fun onBindViewHolder(holder: Holder, loadState: LoadState) {
        holder.bind(loadState)

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FeedLoadStateBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }
}