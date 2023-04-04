package com.example.kotkin_team.storage.presentation.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotkin_team.R
import com.example.kotkin_team.common.view_binding.viewBinding
import com.example.kotkin_team.databinding.FragmentStorageCategoryBinding
import com.example.kotkin_team.storage.domain.model.StorageCategory
import com.example.kotkin_team.storage.presentation.product.StorageProductFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

private const val COLUMN_COUNT = 3

@AndroidEntryPoint
internal class StorageCategoryFragment : Fragment() {
    companion object {
        fun newInstance() = StorageCategoryFragment()
    }

    private val binding by viewBinding(FragmentStorageCategoryBinding::bind)
    private val viewModel: StorageCategoryViewModel by viewModels()

    private val storageCategoryAdapter =
        StorageCategoryAdapter { storageCategory: StorageCategory ->
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    StorageProductFragment.newInstance(storageCategory.id, storageCategory.name)
                )
                .addToBackStack(null).commit()
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_storage_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButton.setOnClickListener {
            // TODO Поиск рецептов
            Toast.makeText(context, "SEARCH", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, COLUMN_COUNT)
            adapter = storageCategoryAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.storageCategoryState.collectLatest {
                when {
                    it.isLoading -> {
                        Toast.makeText(context, "LOADING...", Toast.LENGTH_SHORT).show()
                    }
                    it.error.isNotBlank() -> {
                        Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        storageCategoryAdapter.submitList(it.storageCategory)
                    }
                }
            }
        }
    }
}
