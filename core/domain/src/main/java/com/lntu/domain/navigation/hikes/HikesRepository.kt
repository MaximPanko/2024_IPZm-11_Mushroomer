package com.lntu.domain.navigation.hikes

import com.lntu.domain.navigation.hikes.entity.Hike
import kotlinx.coroutines.flow.Flow

interface HikesRepository {
    suspend fun addNewHike(name: String)
    suspend fun updateHike(id: String, name: String)
    suspend fun deleteHike(id: String)
    suspend fun getHikeById(id: String): Flow<Hike>
}