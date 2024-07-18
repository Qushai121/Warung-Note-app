package com.capstone.warungstock.ui.stockPricingNoteForm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.capstone.warungstock.data.ResultState
import com.capstone.warungstock.data.local.realm.RealmConfig
import com.capstone.warungstock.data.local.realm.model.ItemStock
import com.capstone.warungstock.data.local.realm.model.SmallPackage
import com.capstone.warungstock.repository.ItemStockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.ext.asFlow
import io.realm.kotlin.ext.copyFromRealm
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.notifications.DeletedObject
import io.realm.kotlin.notifications.InitialObject
import io.realm.kotlin.notifications.UpdatedObject
import io.realm.kotlin.types.RealmList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StockPricingNoteFormViewModel @Inject constructor(
    val realmConfig: RealmConfig,
    val itemStockRepository: ItemStockRepository
) : ViewModel() {

    private val _smallPackageDatas = MutableLiveData<List<SmallPackage>>()
    val smallPackageDatas: MutableLiveData<List<SmallPackage>> get() = _smallPackageDatas

    fun createNewStockPiricng(itemName: String) = liveData {
        emitSource(itemStockRepository.createNewStockPiricng(itemName,_smallPackageDatas.value).asLiveData())
    }

    fun addNewSmallPackage(data: SmallPackage) {
        val prevDatas = _smallPackageDatas.value?.toMutableList() ?: mutableListOf()
        prevDatas.add(data)
        _smallPackageDatas.postValue(prevDatas)
    }

    fun getDetailData(id: String) = liveData{
        val datas = itemStockRepository.getDetailItemStock(id).asLiveData()
        emitSource(datas)
    }

    fun initializeDataForEditForm(datas : List<SmallPackage>?) {
        _smallPackageDatas.postValue(datas ?: listOf())
    }

    fun saveEditStockPricing(id: String, itemName: String) = liveData {
        emitSource(itemStockRepository.saveEditStockPricing(id,itemName,_smallPackageDatas.value).asLiveData())
    }

}