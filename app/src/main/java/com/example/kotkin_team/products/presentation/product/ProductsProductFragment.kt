package com.example.kotkin_team.products.presentation.product

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
import com.example.kotkin_team.products.domain.events.ProductsProductEvents
import com.example.kotkin_team.products.domain.model.ProductsProduct
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

private const val ARG_PARENT_ID = "parentId"

@AndroidEntryPoint
internal class ProductsProductFragment : Fragment() {
    companion object {
        fun newInstance(parentId: Int) =
            ProductsProductFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARENT_ID, parentId)
                }
            }
    }

    private val viewModel: ProductsProductViewModel by viewModels()

    private val productsProductAdapter =
        ProductsProductAdapter { productsProduct: ProductsProduct ->

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val parentId = requireArguments().getInt(ARG_PARENT_ID)

        viewModel.onEvent(ProductsProductEvents.LoadProduct(parentId))

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = productsProductAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.productsProductState.collectLatest {
                when (it.isLoading) {
                    true -> {
                        Toast.makeText(context, "LOADING...", Toast.LENGTH_SHORT).show()
                    }
                    false -> {
                        if (it.error.isBlank()) {
                            productsProductAdapter.submitList(it.productsProduct)
                        } else {
                            Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}