package com.example.kotlinTeam.storage.presentation.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlinTeam.R
import com.example.kotlinTeam.common.viewBinding.viewBinding
import com.example.kotlinTeam.databinding.FragmentStorageProductBinding
import com.example.kotlinTeam.storage.domain.events.StorageProductEvents
import com.example.kotlinTeam.storage.domain.model.StorageProduct
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val ARG_PARENT_ID = "parentId"
private const val ARG_PARENT_NAME = "parentName"
private const val COLUMN_COUNT = 3

@AndroidEntryPoint
internal class StorageProductFragment : Fragment() {

    private val binding by viewBinding(FragmentStorageProductBinding::bind)
    private val viewModel: StorageProductViewModel by viewModels()

    private val storageProductAdapter =
        StorageProductAdapter { storageProduct: StorageProduct ->
            viewModel.onEvent(StorageProductEvents.SelectProduct(storageProduct))
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_storage_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val parentId = requireArguments().getString(ARG_PARENT_ID)
        val parentName = requireArguments().getString(ARG_PARENT_NAME)

        viewModel.onEvent(StorageProductEvents.InitProduct(parentId!!))

        binding.backButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_storageProductFragment_to_storageCategoryFragment
            )
        }
        binding.title.text = parentName

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, COLUMN_COUNT)
            adapter = storageProductAdapter
        }

        binding.btnRetry.setOnClickListener {
            viewModel.onEvent(StorageProductEvents.InitProduct(parentId))
            binding.mainProgressBar.visibility = View.VISIBLE
            binding.btnRetry.visibility = View.GONE
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.storageProductState.collectLatest { state ->
                when (!state.isLoading) {
                    true -> {
                        binding.mainProgressBar.visibility = View.GONE
                        if (state.error.isBlank()) {
                            binding.btnRetry.visibility = View.GONE
                            binding.recyclerView.visibility = View.VISIBLE
                            state.storageProduct?.let {
                                storageProductAdapter.submitData(it)
                            }
                        } else {
                            binding.btnRetry.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                    false -> {
                        binding.mainProgressBar.visibility = View.VISIBLE
                        binding.btnRetry.visibility = View.GONE
                        binding.recyclerView.visibility = View.GONE
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.storageSelectProductState.collectLatest { state ->
                when (!state.isLoading) {
                    true -> {
                        binding.mainProgressBar.visibility = View.GONE
                        if (state.error.isBlank()) {
                            storageProductAdapter.notifyItemChanged(
                                storageProductAdapter.snapshot().items.indexOf(
                                    state.storageProduct
                                )
                            )
                        } else {
                            Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                    false -> {
                        binding.mainProgressBar.visibility = View.VISIBLE
                    }
                }
            }
        }

        storageProductAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                binding.mainProgressBar.visibility = View.VISIBLE
            } else {
                binding.mainProgressBar.visibility = View.GONE
            }
        }

        viewModel.parentId.observe(viewLifecycleOwner) {
            viewModel.onEvent(StorageProductEvents.LoadProduct)
        }
    }
}
