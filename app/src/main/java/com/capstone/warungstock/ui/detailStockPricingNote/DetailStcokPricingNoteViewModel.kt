package com.capstone.warungstock.ui.detailStockPricingNote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.capstone.warungstock.data.ResultState
import com.capstone.warungstock.data.local.realm.RealmConfig
import com.capstone.warungstock.data.local.realm.model.ItemStock

class DetailStcokPricingNoteViewModel : ViewModel() {

    fun getStockPricingNoteById(id : String) = liveData {
        emit(ResultState.Loading)
        try {
            val data = RealmConfig.realm.query(ItemStock::class,"_id == oid($id)").first().asFlow()
            data.collect{
                if (it.obj != null){
                    emit(ResultState.Success(it.obj!!))
                }
            }
        }catch (e : Exception){
            emit(
                ResultState.Error(e.toString())
            )
        }
    }

}