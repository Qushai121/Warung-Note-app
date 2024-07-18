package com.capstone.warungstock.repository

import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.capstone.warungstock.data.ResultState
import com.capstone.warungstock.data.local.realm.RealmConfig
import com.capstone.warungstock.data.local.realm.model.ItemStock
import com.capstone.warungstock.data.local.realm.model.SmallPackage
import io.realm.kotlin.ext.asFlow
import io.realm.kotlin.ext.copyFromRealm
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.notifications.DeletedObject
import io.realm.kotlin.notifications.InitialObject
import io.realm.kotlin.notifications.InitialResults
import io.realm.kotlin.notifications.UpdatedObject
import io.realm.kotlin.notifications.UpdatedResults
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemStockRepository @Inject constructor(
    val realmConfig: RealmConfig
) {

    val getItemStockList = flow {
        emit(ResultState.Loading)
        try {
            realmConfig.realm.query<ItemStock>().find().asFlow().collect {
                when (it) {
                    is InitialResults -> {
                        emit(ResultState.Success(it.list))
                    }

                    is UpdatedResults -> {
                        emit(ResultState.Success(it.list))
                    }
                }
            }
        } catch (e: Exception) {
            emit(ResultState.Error(e.message.toString()))
        }
    }

    fun getSearchItemStock(itemStock: String) = flow {
        emit(ResultState.Loading)
        try {
            if (itemStock.isNotBlank()) {
                realmConfig.realm.query<ItemStock>("itemname CONTAINS $0", itemStock).find()
                    .asFlow().collect {
                        when (it) {
                            is InitialResults -> emit(ResultState.Success(it.list))
                            is UpdatedResults -> emit(ResultState.Success(it.list))
                        }
                    }
            } else {
                emit(ResultState.Error("Empty Result"))
            }
        } catch (e: Exception) {
            emit(ResultState.Error("Error"))
        }
    }

    fun createNewStockPiricng(itemName: String, smallPackage: List<SmallPackage>?) = flow {
        emit(ResultState.Loading)
        try {
            realmConfig.realm.write {
                val stockPricingNote = ItemStock().apply {
                    itemname = itemName
                    smalPackages = smallPackage?.toRealmList()
                }
                copyToRealm(stockPricingNote)
            }
            emit(ResultState.Success("Succces"))
        } catch (e: Exception) {
            emit(ResultState.Error("Error"))
        }
    }

    fun saveEditStockPricing(id: String, itemName: String, smallPackage: List<SmallPackage>?) =
        flow {
            emit(ResultState.Loading)
            try {
                realmConfig.realm.write {
                    val datas = query<ItemStock>("_id == oid($id)").find().first()
                    datas.itemname = itemName
                    datas.smalPackages = smallPackage?.toRealmList()
                }
                emit(ResultState.Success("Succces"))
            } catch (e: Exception) {
                emit(ResultState.Error("Error"))

            }
        }

    fun getDetailItemStock(id: String) = flow {
        emit(ResultState.Loading)
        try {
            realmConfig.realm.query<ItemStock>("_id == oid($id)").find().first().asFlow().collect {
                when (it) {
                    is DeletedObject -> {
                        emit(ResultState.Success(it.obj?.copyFromRealm()))
                    }

                    is InitialObject -> {
                        emit(ResultState.Success(it.obj.copyFromRealm()))
                    }

                    is UpdatedObject -> {
                        emit(ResultState.Success(it.obj.copyFromRealm()))
                    }
                }
            }
        } catch (e: Exception) {
            emit(ResultState.Error("Error"))

        }
    }

    fun deleteItemStcok(id: String) = flow {
        emit(ResultState.Loading)
        try {
            realmConfig.realm.write {
                val data = query<ItemStock>("_id == oid($id)").find().first()
                delete(data)
            }
            emit(ResultState.Success("Successfully Delete Item Pricing Note"))
        } catch (e: Exception) {
            emit(ResultState.Error("Error"))
        }
    }
}