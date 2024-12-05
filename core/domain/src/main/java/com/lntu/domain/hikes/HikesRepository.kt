package com.lntu.domain.hikes

import kotlinx.coroutines.flow.Flow

interface HikesRepository {
    suspend fun addNewHike(name: String)
    suspend fun updateHike(id: String, name: String)
    suspend fun deleteHike(id: String)
    suspend fun getAllHikes(): Flow<List<Hike>>
    suspend fun getHikeById(id: String): Flow<Hike>
}