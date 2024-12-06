package com.lntu.domain.mushrooms

import kotlinx.coroutines.flow.Flow

interface MushroomsRepository {
    suspend fun addNewMushroom(hikeId: String, name: String, description: String, weight: Double)
    suspend fun updateMushroom(id: String, name: String, description: String, weight: Double)
    suspend fun deleteMushroom(id: String)
    suspend fun getAllMushrooms(): Flow<List<Mushroom>>
    suspend fun getMushroomById(id: String): Flow<Mushroom>
    suspend fun getMushroomsByHikeId(hikeId: String): Flow<List<Mushroom>>
}