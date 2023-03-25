package com.example.kotkin_team.feed.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotkin_team.databinding.RecipeStepItemBinding
import com.example.kotkin_team.feed.data.Step

class StepsAdapter : RecyclerView.Adapter<StepsAdapter.StepsViewHolder>() {
    class StepsViewHolder(var binding: RecipeStepItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(step: Step) {
            binding.step = step
        }

    }

    var steps: List<Step> = listOf()
    fun setData(data: List<Step>) {
        steps = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsViewHolder {
        return StepsViewHolder(
            RecipeStepItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = steps.size

    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {
        holder.bind(steps[position])
    }
}