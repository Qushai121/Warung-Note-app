package com.capstone.warungstock.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.warungstock.data.local.realm.model.ItemStock
import com.capstone.warungstock.databinding.FragmentHomeBinding
import com.capstone.warungstock.databinding.ItemStockPricingNoteBinding

class HomeFragment : Fragment() {

    lateinit var smallPackageNoteAdapter: SmallPackageNoteAdapter
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

        smallPackageNoteAdapter = SmallPackageNoteAdapter()
        stockPricingNoteAdapter = StockPricingNoteAdapter()
        setupRecylerView()
        setuoObserver()
    }

    private fun setuoObserver() {
        homeViewModel.getItemStock.observe(requireActivity()){
            stockPricingNoteAdapter.submitList(it.toList())
            stockPricingNoteAdapter.setNewListener(object : StockPricingNoteAdapter.AddNewListener{
                override fun setSmallPackageNestedRecylerView(
                    item: ItemStock,
                    binding: ItemStockPricingNoteBinding
                ) {
                    with(binding){
                        smallPackageNoteAdapter.submitList(item.smalPackages)
                        this.recylerViewSmallPackages.apply {
                            adapter = smallPackageNoteAdapter
                            layoutManager = LinearLayoutManager(requireContext(),
                                LinearLayoutManager.HORIZONTAL,false)
                        }
                    }
                }

            })
        }
    }

    private fun setupRecylerView() {
        with(binding){
            recylerViewStockPricingNote.apply {
                adapter = stockPricingNoteAdapter
                layoutManager = LinearLayoutManager(requireContext(),
                    LinearLayoutManager.HORIZONTAL,false)
            }
        }
    }

}