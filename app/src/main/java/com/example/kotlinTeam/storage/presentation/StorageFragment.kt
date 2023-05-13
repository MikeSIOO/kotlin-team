package com.example.kotlinTeam.storage.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlinTeam.R
import com.example.kotlinTeam.common.viewBinding.viewBinding
import com.example.kotlinTeam.databinding.FragmentStorageBinding
import com.example.kotlinTeam.storage.domain.events.StorageEvents
import com.example.kotlinTeam.storage.domain.model.StorageDataModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

private const val COLUMN_COUNT = 3

@AndroidEntryPoint
internal class StorageFragment : Fragment() {

    private val binding by viewBinding(FragmentStorageBinding::bind)
    private val viewModel: StorageViewModel by viewModels()

    private val storageAdapter = StorageAdapter { item: StorageDataModel ->
        if (item is StorageDataModel.StorageCategory) {
            binding.backButton.visibility = View.VISIBLE
            binding.title.text = item.title
            binding.subtitle.visibility = View.GONE
            viewModel.onEvent(StorageEvents.InitProduct(item.id.toString()))
        } else if (item is StorageDataModel.StorageProduct) {
            viewModel.onEvent(StorageEvents.SelectProduct(item))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.backButton.visibility == View.VISIBLE) {
                        binding.title.text = "Выберите продукты"
                        binding.backButton.visibility = View.GONE
                        binding.subtitle.visibility = View.VISIBLE
                        binding.btnRetry.visibility = View.GONE
                        viewModel.onEvent(StorageEvents.InitCategory)
                    } else {
                        requireActivity().finish()
                        exitProcess(0)
                    }
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_storage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRetry.setOnClickListener {
            binding.title.text = "Выберите продукты"
            binding.backButton.visibility = View.GONE
            binding.subtitle.visibility = View.VISIBLE
            binding.btnRetry.visibility = View.GONE
            viewModel.onEvent(StorageEvents.InitCategory)
        }

        binding.backButton.setOnClickListener {
            backPressed()
        }

        binding.search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
//                if (s != "") {
                    viewModel.onEvent(StorageEvents.SearchProduct(s.toString()))
//                } else {
//                    viewModel.onEvent(StorageEvents.InitCategory)
//                }
            }
        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.storageState.collectLatest { state ->
                when (!state.isLoading) {
                    true -> {
                        if (state.error.isBlank()) {
                            binding.btnRetry.visibility = View.GONE
                            binding.recyclerView.visibility = View.VISIBLE
                            state.storageData?.let {
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

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.storageSelectProductState.collectLatest { state ->
                when (!state.isLoading) {
                    true -> {
//                        binding.mainProgressBar.visibility = View.GONE
                        if (state.error.isBlank()) {
                            if (state.storageProduct is StorageDataModel) {
                                storageAdapter.notifyItemChanged(
                                    storageAdapter.snapshot().items.indexOf(
                                        state.storageProduct
                                    )
                                )
                            }
                        } else {
                            Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                        }
                    }

                    false -> {
//                        binding.mainProgressBar.visibility = View.VISIBLE
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

    private fun backPressed() {
        binding.title.text = "Выберите продукты"
        binding.backButton.visibility = View.GONE
        binding.subtitle.visibility = View.VISIBLE
        binding.btnRetry.visibility = View.GONE
        viewModel.onEvent(StorageEvents.InitCategory)
    }
}
