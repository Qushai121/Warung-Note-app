package com.capstone.warungstock.ui.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.capstone.warungstock.R
import com.capstone.warungstock.databinding.FragmentDeleteItemConfirmationDialogBinding

class DeleteItemConfirmationDialogFragment : DialogFragment() {

    lateinit var binding : FragmentDeleteItemConfirmationDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDeleteItemConfirmationDialogBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        with(binding){

        }
    }


}