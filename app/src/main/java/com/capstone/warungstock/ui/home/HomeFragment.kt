package com.capstone.warungstock.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.warungstock.data.local.realm.model.ItemStock
import com.capstone.warungstock.databinding.FragmentHomeBinding
import com.capstone.warungstock.databinding.ItemStockPricingNoteBinding

class HomeFragment : Fragment() {

    lateinit var stockPricingNoteAdapter: StockPricingNoteAdapter
    lateinit var binding : FragmentHomeBinding
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
        setuoObserver()
        setupView()
    }

    private fun setupView() {
        with(binding){
            searchView.setupWithSearchBar(searchBar)
        }
    }

    private fun setupAdapter() {
        stockPricingNoteAdapter = StockPricingNoteAdapter()
        stockPricingNoteAdapter.setNewListener(object : StockPricingNoteAdapter.AddNewListener{
            override fun setSmallPackageNestedRecylerView(
                item: ItemStock,
                binding: ItemStockPricingNoteBinding
            ) {
                val smallPackageNoteAdapter = SmallPackageNoteAdapter()
                with(binding){
                    this@with.recylerViewSmallPackages.apply {
                        adapter = smallPackageNoteAdapter
                        layoutManager = LinearLayoutManager(requireContext(),
                            LinearLayoutManager.HORIZONTAL,false)
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

    private fun setuoObserver() {
        homeViewModel.getItemStock.observe(requireActivity()){
            stockPricingNoteAdapter.submitList(it.toList())
        }
    }

    private fun setupRecylerView() {
        with(binding){
            recylerViewStockPricingNote.apply {
                adapter = stockPricingNoteAdapter
                layoutManager = LinearLayoutManager(requireContext(),
                    LinearLayoutManager.VERTICAL,false)
            }
            fabAdd.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToFormAddStockPricingFragment()
                )
            }
        }
    }

}