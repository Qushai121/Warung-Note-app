package com.capstone.warungstock.ui.DetailStockPricingNote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.warungstock.databinding.FragmentDetailStockPricingNoteBinding

class DetailStockPricingNoteFragment : Fragment() {

    lateinit var binding : FragmentDetailStockPricingNoteBinding
    lateinit var smallPackageNoteAdapter: SmallPackageNoteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailStockPricingNoteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        smallPackageNoteAdapter = SmallPackageNoteAdapter()
        setupRecylerView()
        setuoObserver()
    }

    private fun setuoObserver() {

    }

    private fun setupRecylerView() {
    }
}