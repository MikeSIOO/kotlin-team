package com.example.kotkin_team.storage.presentation.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
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
private const val ARG_PARENT_NAME = "parentName"

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
        val parentName = requireArguments().getString(ARG_PARENT_NAME)

        viewModel.onEvent(StorageProductEvents.LoadProduct(parentId))

        val backButton = view.findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener {
            this.parentFragmentManager.popBackStack()
        }
        val title = view.findViewById<TextView>(R.id.title)
        title.text = parentName
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = storageProductAdapter
        }
        val searchButton = view.findViewById<Button>(R.id.search_button)
        searchButton.setOnClickListener {
//            TODO("Поиск рецептов")
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