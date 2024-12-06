package com.lntu.domain.mushrooms

import kotlinx.coroutines.flow.Flow

interface MushroomsRepository {
    suspend fun addNewMushroom(name: String)
    suspend fun updateMushroom(id: String, name: String)
    suspend fun deleteMushroom(id: String)
    suspend fun getAllMushrooms(): Flow<List<Mushroom>>
    suspend fun getMushroomById(id: String): Flow<Mushroom>
}