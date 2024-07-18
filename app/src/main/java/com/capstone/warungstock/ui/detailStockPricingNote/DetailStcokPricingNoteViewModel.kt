package com.capstone.warungstock.ui.detailStockPricingNote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.capstone.warungstock.data.ResultState
import com.capstone.warungstock.data.local.realm.RealmConfig
import com.capstone.warungstock.data.local.realm.model.ItemStock
import com.capstone.warungstock.repository.ItemStockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailStcokPricingNoteViewModel @Inject constructor(
    val itemStockRepository: ItemStockRepository
) : ViewModel() {
    fun getStockPricingNoteById(id: String) = liveData {
        val datas = itemStockRepository.getDetailItemStock(id).asLiveData()
        emitSource(datas)
    }

}