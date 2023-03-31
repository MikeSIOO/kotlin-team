package com.example.kotkin_team.storage.presentation.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotkin_team.R
import com.example.kotkin_team.common.view_binding.viewBinding
import com.example.kotkin_team.databinding.FragmentStorageCategoryBinding
import com.example.kotkin_team.databinding.FragmentStorageProductBinding
import com.example.kotkin_team.storage.domain.events.StorageProductEvents
import com.example.kotkin_team.storage.domain.model.StorageProduct
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

private const val ARG_PARENT_ID = "parentId"
private const val ARG_PARENT_NAME = "parentName"
private const val COLUMN_COUNT = 3

@AndroidEntryPoint
internal class StorageProductFragment : Fragment() {
    companion object {
        fun newInstance(parentId: Int, parentName: String) =
            StorageProductFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARENT_ID, parentId)
                    putString(ARG_PARENT_NAME, parentName)
                }
            }
    }

    private val binding by viewBinding(FragmentStorageProductBinding::bind)
    private val viewModel: StorageProductViewModel by viewModels()

    private val storageProductAdapter =
        StorageProductAdapter { storageProduct: StorageProduct ->
            viewModel.onEvent(StorageProductEvents.SelectProduct(storageProduct))
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
        val parentName = requireArguments().getString(ARG_PARENT_NAME)

        viewModel.onEvent(StorageProductEvents.LoadProduct(parentId))

        binding.backButton.setOnClickListener {
            this.parentFragmentManager.popBackStack()
        }
        binding.title.text = parentName
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, COLUMN_COUNT)
            adapter = storageProductAdapter
        }
        binding.searchButton.setOnClickListener {
            // TODO Поиск рецептов
            Toast.makeText(context, "SEARCH", Toast.LENGTH_SHORT).show()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.storageProductState.collectLatest {
                when {
                    it.isLoading -> {
                        Toast.makeText(context, "LOADING...", Toast.LENGTH_SHORT).show()
                    }
                    it.error.isNotBlank() -> {
                        Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        storageProductAdapter.submitList(it.storageProduct)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.storageSelectProductState.collectLatest {
                when {
                    it.isLoading -> {
                        Toast.makeText(context, "LOADING SELECT", Toast.LENGTH_SHORT).show()
                    }
                    it.error.isNotBlank() -> {
                        Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        storageProductAdapter.notifyItemChanged(
                            storageProductAdapter.currentList.indexOf(
                                it.storageProduct
                            )
                        )
                    }
                }
            }
        }
    }
}
