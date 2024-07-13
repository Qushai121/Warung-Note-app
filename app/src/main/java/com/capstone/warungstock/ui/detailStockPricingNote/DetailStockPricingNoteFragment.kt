package com.capstone.warungstock.ui.detailStockPricingNote

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.warungstock.data.ResultState
import com.capstone.warungstock.databinding.FragmentDetailStockPricingNoteBinding

class DetailStockPricingNoteFragment : Fragment() {

    lateinit var binding : FragmentDetailStockPricingNoteBinding
    lateinit var smallPackageNoteAdapter: SmallPackageNoteAdapter
    val detailStcokPricingNoteViewModel : DetailStcokPricingNoteViewModel by viewModels()
    val args : DetailStockPricingNoteFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailStockPricingNoteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        smallPackageNoteAdapter = SmallPackageNoteAdapter()
        setupRecylerView()
        setupObserver()
    }

    private fun setupObserver() {
        detailStcokPricingNoteViewModel.getStockPricingNoteById(args.idStockItemPricing).observe(viewLifecycleOwner){when(it){
            is ResultState.Error -> {
                Log.i("datas",it.toString())
            }
            ResultState.Loading -> {
                Log.i("datas","loading".toString())

            }
            is ResultState.Success -> {
                Log.i("datas",it.data.toString())
                with(binding){
                    textViewDetailItemName.text = it.data.itemname
                }
                smallPackageNoteAdapter.submitList(it.data.smalPackages)
            }
        }
        }
    }

    private fun setupRecylerView() {
        with(binding){
            recylerViewDetailSmallPackages.apply {
                adapter = smallPackageNoteAdapter
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            }
        }
    }
}