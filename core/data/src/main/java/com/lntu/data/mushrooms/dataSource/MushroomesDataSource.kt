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
    suspend fun addNewMushroom(name: String) : MushroomesDb {
        return withContext(ioDispatcher) {
            val mushroomDb = MushroomesDb().apply {
                this.name = name
                this.description = description
                this.weight = weight
            }
            realm.writeBlocking {
                copyToRealm(mushroomDb)
            }
        }
    }

    suspend fun updateMushroom(id: String, name: String) : MushroomesDb {
        return withContext(ioDispatcher) {
            realm.writeBlocking {
                this.query<MushroomesDb>("_id == $0", ObjectId(id))
                    .find()
                    .first()
                    .apply {
                        this.name = name
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
}