package com.capstone.warungstock.data.local.realm

import com.capstone.warungstock.data.local.realm.model.ItemStock
import com.capstone.warungstock.data.local.realm.model.SmallPackage
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

object RealmConfig {
    private val config = RealmConfiguration.create(schema = setOf(
        ItemStock::class,
        SmallPackage::class
    ))
    val realm: Realm = Realm.open(config)
}