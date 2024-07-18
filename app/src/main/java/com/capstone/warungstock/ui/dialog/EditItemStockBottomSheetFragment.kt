package com.capstone.warungstock.ui.dialog

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.capstone.warungstock.R
import com.capstone.warungstock.databinding.FragmentEditItemStockBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EditItemStockBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentEditItemStockBottomSheetBinding
    private val args: EditItemStockBottomSheetFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditItemStockBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        with(binding) {
            btnEditForm.setOnClickListener {
                findNavController().navigate(
                    EditItemStockBottomSheetFragmentDirections.actionEditItemStockBottomSheetFragmentToFormEditStockPricingFragment(
                        args.idStockItemPricing
                    )
                )
            }
            btnDelete.setOnClickListener {
                findNavController().navigate(
                    EditItemStockBottomSheetFragmentDirections.actionEditItemStockBottomSheetFragmentToDeleteItemConfirmationDialogFragment(
                        idStockItemPricing = args.idStockItemPricing,
                        stockItemName = args.stockItemName
                    )
                )
            }
        }
    }

    companion object {
        const val TAG = "EditItemStockBottomSheetFragment"
    }

}