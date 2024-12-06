package com.lntu.data.mushrooms.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

class MushroomesDb: RealmObject {
    @PrimaryKey
    var id: ObjectId = BsonObjectId()
    var hikeId: String = ""
    var name: String = ""
    var description: String = ""
    var weight: Double = 0.0
}