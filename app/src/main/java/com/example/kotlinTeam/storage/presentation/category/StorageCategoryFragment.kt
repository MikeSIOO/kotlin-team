package com.example.kotlinTeam.storage.presentation.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlinTeam.R
import com.example.kotlinTeam.common.viewBinding.viewBinding
import com.example.kotlinTeam.databinding.FragmentStorageCategoryBinding
import com.example.kotlinTeam.storage.domain.events.StorageCategoryEvents
import com.example.kotlinTeam.storage.domain.model.StorageCategory
import com.example.kotlinTeam.storage.presentation.StorageAdapter
import com.example.kotlinTeam.storage.presentation.StorageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val COLUMN_COUNT = 3

@AndroidEntryPoint
internal class StorageCategoryFragment : Fragment() {

    private val binding by viewBinding(FragmentStorageCategoryBinding::bind)
    private val viewModel: StorageViewModel by viewModels()

    private val storageAdapter = StorageAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("!@#", "create!")
        return inflater.inflate(R.layout.fragment_storage_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRetry.setOnClickListener {
            viewModel.onEvent(StorageCategoryEvents.InitCategory)
            binding.btnRetry.visibility = View.GONE
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.storageCategoryState.collectLatest { state ->
                when (!state.isLoading) {
                    true -> {
                        if (state.error.isBlank()) {
                            binding.btnRetry.visibility = View.GONE
                            binding.recyclerView.visibility = View.VISIBLE
                            state.storageCategory?.let {
                                storageAdapter.submitData(it)
                            }
                        } else {
                            binding.btnRetry.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                    false -> {
                        binding.btnRetry.visibility = View.GONE
                        binding.recyclerView.visibility = View.GONE
                    }
                }
            }
        }

        storageAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                binding.mainProgressBar.visibility = View.VISIBLE
            } else {
                binding.mainProgressBar.visibility = View.GONE
            }
        }

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, COLUMN_COUNT)
            adapter = storageAdapter
        }
    }
/*
    private val binding by viewBinding(FragmentStorageCategoryBinding::bind)
    private val viewModel: StorageCategoryViewModel by viewModels()

    private val storageCategoryAdapter =
        StorageCategoryAdapter { storageCategory: StorageCategory ->
            val bundle = bundleOf(
                "parentId" to storageCategory.id,
                "parentName" to storageCategory.title
            )
            findNavController().navigate(
                R.id.action_storageCategoryFragment_to_storageProductFragment,
                bundle
            )
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_storage_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRetry.setOnClickListener {
            viewModel.onEvent(StorageCategoryEvents.InitCategory)
            binding.btnRetry.visibility = View.GONE
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.storageCategoryState.collectLatest { state ->
                when (!state.isLoading) {
                    true -> {
                        if (state.error.isBlank()) {
                            binding.btnRetry.visibility = View.GONE
                            binding.recyclerView.visibility = View.VISIBLE
                            state.storageCategory?.let {
//                                storageCategoryAdapter.submitData(it)
                            }
                        } else {
                            binding.btnRetry.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                    false -> {
                        binding.btnRetry.visibility = View.GONE
                        binding.recyclerView.visibility = View.GONE
                    }
                }
            }
        }

        storageCategoryAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                binding.mainProgressBar.visibility = View.VISIBLE
            } else {
                binding.mainProgressBar.visibility = View.GONE
            }
        }

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, COLUMN_COUNT)
            adapter = storageCategoryAdapter
        }
    }*/
}
