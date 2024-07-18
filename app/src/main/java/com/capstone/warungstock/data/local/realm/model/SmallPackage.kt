package com.capstone.warungstock.data.local.realm.model

import io.realm.kotlin.types.EmbeddedRealmObject
class SmallPackage : EmbeddedRealmObject{
    var packagename : String = ""
    var packageprice : Int = 0
}