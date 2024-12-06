package com.lntu.data.mushrooms.dataSource

import com.lntu.data.mushrooms.entity.MushroomesDb
import com.lntu.domain.coroutines.IoDispatcher
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

class MushroomesDataSource @Inject constructor(
    private val realm: Realm,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun addNewMushroom(hikeId: String, name: String, description: String, weight: Double) : MushroomesDb {
        return withContext(ioDispatcher) {
            val mushroomDb = MushroomesDb().apply {
                this.hikeId = ObjectId(hikeId).toString()
                this.name = name
                this.description = description
                this.weight = weight
            }
            realm.writeBlocking {
                copyToRealm(mushroomDb)
            }
        }
    }

    suspend fun updateMushroom(id: String, name: String, description: String, weight: Double) : MushroomesDb {
        return withContext(ioDispatcher) {
            realm.writeBlocking {
                this.query<MushroomesDb>("_id == $0", ObjectId(id))
                    .find()
                    .first()
                    .apply {
                        this.name = name
                        this.description = description
                        this.weight = weight
                    }
            }
        }
    }

    suspend fun deleteMushroom(id: String) {
        return withContext(ioDispatcher) {
            realm.writeBlocking {
                this.query<MushroomesDb>("_id == $0", ObjectId(id))
                    .find()
                    .first()
                    .let { delete(it) }
            }
        }
    }

    fun getAllMushrooms(): Flow<List<MushroomesDb>> {
        return realm
            .query<MushroomesDb>()
            .asFlow()
            .map { it.list }
    }

    fun getMushroomById(id: String): Flow<MushroomesDb> {
        return realm
            .query<MushroomesDb>("_id == $0", ObjectId(id))
            .first()
            .asFlow()
            .map { it.obj!! }
    }

    fun getMushroomByHikeId(hikeId: String): Flow<List<MushroomesDb>> {
        return realm
            .query<MushroomesDb>("hikeId == $0", hikeId)
            .asFlow()
            .map { it.list }
    }
}