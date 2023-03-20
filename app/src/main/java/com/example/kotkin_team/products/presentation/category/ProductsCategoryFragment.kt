package com.example.kotkin_team.products.presentation.category

import android.os.Bundle
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
import com.example.kotkin_team.products.domain.events.ProductsCategoryEvents
import com.example.kotkin_team.products.domain.model.ProductsCategory
import com.example.kotkin_team.products.presentation.product.ProductsProductFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
internal class ProductsCategoryFragment : Fragment() {
    companion object {
        fun newInstance() =
            ProductsCategoryFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    private val viewModel: ProductsCategoryViewModel by viewModels()

    private val productsCategoryAdapter =
        ProductsCategoryAdapter { productsCategory: ProductsCategory ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, ProductsProductFragment.newInstance(productsCategory.id))
                .addToBackStack(null).commit()
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onEvent(ProductsCategoryEvents.LoadCategory())

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = productsCategoryAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.productsCategoryState.collectLatest {
                when (it.isLoading) {
                    true -> {
                        Toast.makeText(context, "LOADING...", Toast.LENGTH_SHORT).show()
                    }
                    false -> {
                        if (it.error.isBlank()) {
                            productsCategoryAdapter.submitList(it.productsCategory)
                        } else {
                            Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}