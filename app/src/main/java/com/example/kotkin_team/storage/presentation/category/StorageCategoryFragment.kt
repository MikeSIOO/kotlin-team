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
import androidx.recyclerview.widget.RecyclerView
import com.example.kotkin_team.R
import com.example.kotkin_team.storage.domain.events.StorageCategoryEvents
import com.example.kotkin_team.storage.domain.model.StorageCategory
import com.example.kotkin_team.storage.presentation.product.StorageProductFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
internal class StorageCategoryFragment : Fragment() {
    companion object {
        fun newInstance() =
            StorageCategoryFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    private val viewModel: StorageCategoryViewModel by viewModels()

    private val storageCategoryAdapter =
        StorageCategoryAdapter { storageCategory: StorageCategory ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, StorageProductFragment.newInstance(storageCategory.id))
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

        viewModel.onEvent(StorageCategoryEvents.LoadCategory)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = storageCategoryAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.storageCategoryState.collectLatest {
                when (it.isLoading) {
                    true -> {
                        Toast.makeText(context, "LOADING...", Toast.LENGTH_SHORT).show()
                    }
                    false -> {
                        if (it.error.isBlank()) {
                            storageCategoryAdapter.submitList(it.storageCategory)
                        } else {
                            Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}