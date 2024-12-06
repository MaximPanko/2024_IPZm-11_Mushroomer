package com.lntu.data.mushrooms

import com.lntu.data.mushrooms.dataSource.MushroomesDataSource
import com.lntu.data.mushrooms.dataSource.MushroomesMapper
import com.lntu.domain.mushrooms.Mushroom
import com.lntu.domain.mushrooms.MushroomsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MushroomesRepositoryImpl @Inject constructor(
    private val mushroomsDataSource: MushroomesDataSource
    ) : MushroomsRepository {

    override suspend fun addNewMushroom(name: String) {
        mushroomsDataSource.addNewMushroom(
            name = name
        )
    }

    override suspend fun updateMushroom(id: String, name: String) {
        mushroomsDataSource.updateMushroom(
            id = id,
            name = name
        )
    }

    override suspend fun deleteMushroom(id: String) {
        mushroomsDataSource.deleteMushroom(
            id = id
        )
    }

    override suspend fun getAllMushrooms(): Flow<List<Mushroom>> {
        return mushroomsDataSource.getAllMushrooms().map { it.map { MushroomesMapper().mapFromMushroomsDbToMushroom(it) } }
    }

    override suspend fun getMushroomById(id: String): Flow<Mushroom> {
        return mushroomsDataSource.getMushroomById(id).map { MushroomesMapper().mapFromMushroomsDbToMushroom(it) }
    }
}