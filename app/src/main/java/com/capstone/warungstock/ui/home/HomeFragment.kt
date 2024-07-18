package com.capstone.warungstock.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.warungstock.data.ResultState
import com.capstone.warungstock.data.local.realm.model.ItemStock
import com.capstone.warungstock.databinding.FragmentHomeBinding
import com.capstone.warungstock.databinding.ItemStockPricingNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var stockPricingNoteAdapter: StockPricingNoteAdapter
    lateinit var stockPricingNoteAdapterForSearch: StockPricingNoteAdapter
    lateinit var binding: FragmentHomeBinding
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupRecylerView()
        setupObserver()
        setupView()
    }

    private fun setupView() {
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (text.isNullOrBlank()) {
                        binding.includeLayoutHomeSearchView.recylerViewItemStockForSearch.visibility = View.GONE
                        stockPricingNoteAdapterForSearch.submitList(listOf(ItemStock()))
                    }
                    homeViewModel.getSearchItemStock(text.toString() ?: "")
                        .observe(viewLifecycleOwner) {
                            when (it) {
                                is ResultState.Error -> {

                                }

                                ResultState.Loading -> {

                                }

                                is ResultState.Success -> {
                                    binding.includeLayoutHomeSearchView.recylerViewItemStockForSearch.visibility = View.VISIBLE
                                    stockPricingNoteAdapterForSearch.submitList(it.data)
                                }
                            }
                        }
                }

                override fun afterTextChanged(text: Editable?) {

                }

            })
        }
    }

    private fun setupAdapter() {
        stockPricingNoteAdapter = StockPricingNoteAdapter().apply {
            setNewListener(object : StockPricingNoteAdapter.AddNewListener {
                override fun setSmallPackageNestedRecylerView(
                    item: ItemStock,
                    binding: ItemStockPricingNoteBinding
                ) {
                    val smallPackageNoteAdapter = SmallPackageNoteAdapter()
                    with(binding) {
                        this@with.recylerViewSmallPackages.apply {
                            adapter = smallPackageNoteAdapter
                            layoutManager = LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL, false
                            )
                        }
                        smallPackageNoteAdapter.submitList(item.smalPackages)
                    }
                }

                override fun setOnCLickListener(item: ItemStock, binding: ItemStockPricingNoteBinding) {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToDetailStockPricingNoteFragment(item._id.toHexString())
                    )
                }
            })
        }
        stockPricingNoteAdapterForSearch = StockPricingNoteAdapter().apply {
            setNewListener(object : StockPricingNoteAdapter.AddNewListener {
                override fun setSmallPackageNestedRecylerView(
                    item: ItemStock,
                    binding: ItemStockPricingNoteBinding
                ) {
                    val smallPackageNoteAdapter = SmallPackageNoteAdapter()
                    with(binding) {
                        this@with.recylerViewSmallPackages.apply {
                            adapter = smallPackageNoteAdapter
                            layoutManager = LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL, false
                            )
                        }
                        smallPackageNoteAdapter.submitList(item.smalPackages)
                    }
                }

                override fun setOnCLickListener(item: ItemStock, binding: ItemStockPricingNoteBinding) {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToDetailStockPricingNoteFragment(item._id.toHexString())
                    )
                }
            })
        }
    }

    private fun setupObserver() {
        homeViewModel.getItemStockList.observe(requireActivity()) {
            when (it) {
                is ResultState.Error -> {

                }

                ResultState.Loading -> {

                }

                is ResultState.Success -> {
                    stockPricingNoteAdapter.submitList(it.data.toList())
                }
            }
        }
    }

    private fun setupRecylerView() {
        with(binding) {
            recylerViewStockPricingNote.apply {
                adapter = stockPricingNoteAdapter
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL, false
                )
            }
            fabAdd.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToFormAddStockPricingFragment()
                )
            }
            with(includeLayoutHomeSearchView) {
                recylerViewItemStockForSearch.apply {
                    adapter = stockPricingNoteAdapterForSearch
                    layoutManager = LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.VERTICAL, false
                    )
                }
            }
        }
    }

}