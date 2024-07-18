package com.capstone.warungstock.ui.home

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
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.notifications.InitialResults
import io.realm.kotlin.notifications.UpdatedResults
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val itemStockRepository: ItemStockRepository
) : ViewModel() {

    val getItemStockList = liveData {
        emitSource(
            itemStockRepository.getItemStockList.asLiveData()
        )
    }

    fun getSearchItemStock(itemName : String) = liveData{
        emitSource(itemStockRepository.getSearchItemStock(itemName).asLiveData())
    }

}