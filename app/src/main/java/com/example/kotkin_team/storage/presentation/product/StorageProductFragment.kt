package com.example.kotkin_team.storage.presentation.product

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
import com.example.kotkin_team.storage.domain.events.StorageProductEvents
import com.example.kotkin_team.storage.domain.model.StorageProduct
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

private const val ARG_PARENT_ID = "parentId"

@AndroidEntryPoint
internal class StorageProductFragment : Fragment() {
    companion object {
        fun newInstance(parentId: Int) =
            StorageProductFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARENT_ID, parentId)
                }
            }
    }

    private val viewModel: StorageProductViewModel by viewModels()

    private val storageProductAdapter =
        StorageProductAdapter { storageProduct: StorageProduct ->

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_storage_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val parentId = requireArguments().getInt(ARG_PARENT_ID)

        viewModel.onEvent(StorageProductEvents.LoadProduct(parentId))

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = storageProductAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.storageProductState.collectLatest {
                when (it.isLoading) {
                    true -> {
                        Toast.makeText(context, "LOADING...", Toast.LENGTH_SHORT).show()
                    }
                    false -> {
                        if (it.error.isBlank()) {
                            storageProductAdapter.submitList(it.storageProduct)
                        } else {
                            Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}