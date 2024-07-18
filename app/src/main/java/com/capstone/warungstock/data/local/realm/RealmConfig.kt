package com.capstone.warungstock.data.local.realm

import com.capstone.warungstock.data.local.realm.model.ItemStock
import com.capstone.warungstock.data.local.realm.model.SmallPackage
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Inject

class RealmConfig @Inject constructor() {

    private val config = RealmConfiguration.Builder(
        schema = setOf(
            ItemStock::class,
            SmallPackage::class
        )
    ).schemaVersion(3)
        .deleteRealmIfMigrationNeeded()
        .build()
    val realm: Realm = Realm.open(config)

}