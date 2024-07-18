package com.capstone.warungstock.ui.stockPricingNoteForm.add

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.warungstock.data.ResultState
import com.capstone.warungstock.data.local.realm.model.SmallPackage
import com.capstone.warungstock.databinding.FragmentFormAddStockPricingBinding
import com.capstone.warungstock.ui.dialog.SuccessDialogFragment
import com.capstone.warungstock.ui.stockPricingNoteForm.SmallPackageFormAdapter
import com.capstone.warungstock.ui.stockPricingNoteForm.StockPricingNoteFormViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FormAddStockPricingFragment : Fragment() {
    private lateinit var binding : FragmentFormAddStockPricingBinding
    private lateinit var smallPackageFormAdapter: SmallPackageFormAdapter
    private val stockPricingNoteFormViewModel : StockPricingNoteFormViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFormAddStockPricingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupRecylerView()
        setupView()
    }

    private fun setupRecylerView() {
        with(binding){
            recylerViewDetailSmallPackages.apply {
                adapter = smallPackageFormAdapter
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            }
        }
    }

    private fun setupAdapter() {
        smallPackageFormAdapter = SmallPackageFormAdapter()
        stockPricingNoteFormViewModel.smallPackageDatas.observe(viewLifecycleOwner){
            smallPackageFormAdapter.submitList(it)
        }
    }

    private fun setupView() {
        with(binding){
            myToolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            fabSave.setOnClickListener {
                stockPricingNoteFormViewModel.createNewStockPiricng(
                    textInputName.text.toString()
                ).observe(
                    requireActivity()
                ){
                    when(it){
                        is ResultState.Error -> {
                        }
                        ResultState.Loading -> {
                        }
                        is ResultState.Success -> {
                            SuccessDialogFragment().show(
                                childFragmentManager,
                                SuccessDialogFragment.TAG
                            )
                        }
                    }
                }
            }
            btnAddSmallPackageForm.setOnClickListener {
                stockPricingNoteFormViewModel.addNewSmallPackage(SmallPackage().apply {
                    packageprice = 0
                    packagename = ""
                })
            }
        }
    }


}