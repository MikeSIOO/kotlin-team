package com.example.kotkin_team.products.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotkin_team.R
import com.example.kotkin_team.products.domain.model.Category
import com.example.kotkin_team.products.presentation.category.CategoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

private const val ARG_PAGE = "page"

@AndroidEntryPoint
internal class ProductsFragment : Fragment() {
    companion object {
        fun newInstance(page: Int) =
            ProductsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PAGE, page)
                }
            }
    }

    private var page: Int = 1

    private val viewModel: ProductsViewModel by viewModels()

    private val categoryAdapter = CategoryAdapter { category: Category ->
        Log.i("!@#", category.id.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onEvent(Events.LoadCategory(page))

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = categoryAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest {
                when (it.isLoading) {
                    true -> {
                        Toast.makeText(context, "LOADING...", Toast.LENGTH_SHORT).show()
                    }
                    false -> {
                        if (it.error.isBlank()) {
                            categoryAdapter.submitList(it.category)
                        } else {
                            Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}