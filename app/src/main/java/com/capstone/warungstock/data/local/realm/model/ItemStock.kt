package com.capstone.warungstock.data.local.realm.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class ItemStock: RealmObject {
    @PrimaryKey
    var _id : ObjectId = ObjectId()
    var itemname : String = ""
    var smalPackages : RealmList<SmallPackage>? = realmListOf()
}