package com.example.kotlinTeam.storage.presentation.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlinTeam.R
import com.example.kotlinTeam.common.viewBinding.viewBinding
import com.example.kotlinTeam.databinding.FragmentStorageCategoryBinding
import com.example.kotlinTeam.storage.domain.events.StorageCategoryEvents
import com.example.kotlinTeam.storage.domain.model.StorageCategory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

private const val COLUMN_COUNT = 3

@AndroidEntryPoint
internal class StorageCategoryFragment : Fragment() {

    private val binding by viewBinding(FragmentStorageCategoryBinding::bind)
    private val viewModel: StorageCategoryViewModel by viewModels()

    private val storageCategoryAdapter =
        StorageCategoryAdapter { storageCategory: StorageCategory ->
            val bundle = bundleOf(
                "parentId" to storageCategory.id,
                "parentName" to storageCategory.name
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

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, COLUMN_COUNT)
            adapter = storageCategoryAdapter
        }

        binding.btnRetry.setOnClickListener {
            viewModel.onEvent(StorageCategoryEvents.InitCategory)
            binding.mainProgressBar.visibility = View.VISIBLE
            binding.btnRetry.visibility = View.GONE
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.storageCategoryState.collectLatest {
                when {
                    it.isLoading -> {
                        binding.mainProgressBar.visibility = View.VISIBLE
                    }

                    it.error.isNotBlank() -> {
                        binding.btnRetry.visibility = View.VISIBLE
                        Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        binding.mainProgressBar.visibility = View.GONE
                        binding.btnRetry.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                        storageCategoryAdapter.submitList(it.storageCategory)
                    }
                }
            }
        }
    }
}
