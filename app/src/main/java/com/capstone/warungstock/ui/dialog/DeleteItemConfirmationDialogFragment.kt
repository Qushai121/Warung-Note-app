package com.capstone.warungstock.ui.dialog

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.capstone.warungstock.R
import com.capstone.warungstock.data.ResultState
import com.capstone.warungstock.databinding.FragmentDeleteItemConfirmationDialogBinding
import com.capstone.warungstock.ui.dialog.viewmodel.DialogViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteItemConfirmationDialogFragment : DialogFragment() {

    lateinit var binding: FragmentDeleteItemConfirmationDialogBinding
    private val args: DeleteItemConfirmationDialogFragmentArgs by navArgs()
    private val dialogViewModel : DialogViewModel by viewModels()
    override fun onStart() {
        super.onStart()

        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val getDisplayDimension = resources.displayMetrics
        this.dialog?.window?.setLayout(
            (getDisplayDimension.widthPixels * 9) / 10,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDeleteItemConfirmationDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()

    }

    private fun setupView() {
        with(binding) {
            textViewItemName.text = args.stockItemName
            btnCancel.setOnClickListener {
                dialog?.dismiss()
            }
            btnDelete.setOnClickListener {
                dialogViewModel.deleteItemStock(args.idStockItemPricing).observe(viewLifecycleOwner){
                    when(it){
                        is ResultState.Error -> {
                        }
                        ResultState.Loading -> {
                        }
                        is ResultState.Success -> {
                            findNavController().navigate(
                                DeleteItemConfirmationDialogFragmentDirections.actionDeleteItemConfirmationDialogFragmentToHomeFragment()
                            )
                        }
                    }
                }
            }
        }
    }


}