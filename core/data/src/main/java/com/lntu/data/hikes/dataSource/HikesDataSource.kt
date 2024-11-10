package com.lntu.data.hikes.dataSource

import com.lntu.data.hikes.entity.HikesDb
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

class HikesDataSource @Inject constructor(
    private val realm: Realm,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun addNewHike(name: String) : HikesDb {
        return withContext(ioDispatcher) {
            val hikesDb = HikesDb().apply {
                this.name = name
                this.date = System.currentTimeMillis()
            }
            realm.writeBlocking {
                copyToRealm(hikesDb)
            }
        }
    }

    suspend fun updateHike(id: String, name: String) : HikesDb {
        return withContext(ioDispatcher) {
            realm.writeBlocking {
                this.query<HikesDb>("_id == $0", ObjectId(id))
                    .find()
                    .first()
                    .apply {
                        this.name = name
                    }
            }
        }
    }

    suspend fun deleteHike(id: String) {
        return withContext(ioDispatcher) {
            realm.writeBlocking {
                this.query<HikesDb>("_id == $0", ObjectId(id))
                    .find()
                    .first()
                    .let { delete(it) }
            }
        }
    }

    fun getHikeById(id: String): Flow<HikesDb> {
        return realm
            .query<HikesDb>("_id == $0", ObjectId(id))
            .first()
            .asFlow()
            .map { it.obj!! }
    }
}