package com.capstone.warungstock.ui.dialog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.capstone.warungstock.repository.ItemStockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DialogViewModel
    @Inject constructor(
        private val itemStockRepository: ItemStockRepository
    )
    : ViewModel() {

    fun deleteItemStock(id : String) = liveData{
        emitSource(itemStockRepository.deleteItemStcok(id).asLiveData())
    }

}