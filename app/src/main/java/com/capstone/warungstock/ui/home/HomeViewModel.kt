package com.capstone.warungstock.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.capstone.warungstock.data.local.realm.RealmConfig
import com.capstone.warungstock.data.local.realm.model.ItemStock
import com.capstone.warungstock.data.local.realm.model.SmallPackage
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    init {
        viewModelScope.launch {
            RealmConfig.realm.write {
                val itemStock = ItemStock().apply {
                    itemname = "Rinso"
                }
                val itemstock2 = ItemStock().apply {
                    itemname = "Bumbu Racik"
                }
                val smallPackage1 = SmallPackage().apply {
                    packagename = "1 pcs"
                    packageprice = 1_000
                }
                val smallPackage2 = SmallPackage().apply {
                    packagename = "10 pcs"
                    packageprice = 9_000
                }
                itemStock.smalPackages?.addAll(
                    realmListOf(
                        smallPackage1,
                        smallPackage2
                    )
                )
                copyToRealm(itemStock,UpdatePolicy.ALL)
                copyToRealm(itemstock2,UpdatePolicy.ALL)
            }
        }

    }

    val getItemStock = liveData{
        val datas = RealmConfig.realm.query<ItemStock>().find()
        emit(datas)
    }


}