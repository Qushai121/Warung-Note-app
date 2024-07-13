package com.capstone.warungstock.ui.stockPricingNoteForm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.warungstock.databinding.FragmentFormAddStockPricingBinding
import com.capstone.warungstock.ui.dialog.SuccessDialogFragment

class FormAddStockPricingFragment : Fragment() {
    private lateinit var binding : FragmentFormAddStockPricingBinding
    private lateinit var smallPackageFormAdapter: SmallPackageFormAdapter
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
    }

    private fun setupView() {
        with(binding){
            fabSave.setOnClickListener {
                SuccessDialogFragment().show(
                    childFragmentManager,
                    SuccessDialogFragment.TAG
                )
            }
        }
    }


}