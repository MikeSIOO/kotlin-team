package com.example.kotkin_team.feed.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.kotkin_team.R
import com.example.kotkin_team.databinding.FragmentMatchBinding

class MatchFragment : Fragment() {

    private lateinit var binding: FragmentMatchBinding
    private var recipeId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.let {
            recipeId = it.getInt(RECIPE_ID)
        }
        binding = FragmentMatchBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backButton = binding.backToFeedButton
        backButton.setOnClickListener {
            activity?.supportFragmentManager?.let {
                val transaction = it.beginTransaction()
                transaction
                    .replace(R.id.fragment_container, FeedFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    companion object {
        private const val RECIPE_ID = "recipe_id"

        fun newInstance(recipe_id: Int) = MatchFragment().apply {
            arguments = bundleOf(
                RECIPE_ID to recipe_id,
            )
        }
    }
}
