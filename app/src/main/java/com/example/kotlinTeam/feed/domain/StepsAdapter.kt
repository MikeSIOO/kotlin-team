package com.example.kotlinTeam.feed.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinTeam.common.data.dataSource.model.recipe.StepOo
import com.example.kotlinTeam.databinding.RecipeStepItemBinding

class StepsAdapter : RecyclerView.Adapter<StepsAdapter.StepsViewHolder>() {
    class StepsViewHolder(private val binding: RecipeStepItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(step: StepOo) {
            binding.step = step
        }
    }

    var steps: List<StepOo> = listOf()
    fun setData(data: List<StepOo>) {
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
