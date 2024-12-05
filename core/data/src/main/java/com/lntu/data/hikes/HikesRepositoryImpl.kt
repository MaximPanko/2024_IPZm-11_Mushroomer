package com.lntu.data.hikes

import com.lntu.data.hikes.dataSource.HikesDataSource
import com.lntu.data.hikes.dataSource.HikesMapper
import com.lntu.domain.hikes.HikesRepository
import com.lntu.domain.hikes.Hike
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class HikesRepositoryImpl @Inject constructor(
    private val hikesDataSource: HikesDataSource
) : HikesRepository {

    override suspend fun addNewHike(name: String) {
        hikesDataSource.addNewHike(
            name = name
        )
    }

    override suspend fun updateHike(id: String, name: String) {
        hikesDataSource.updateHike(
            id = id,
            name = name
        )
    }

    override suspend fun deleteHike(id: String) {
        hikesDataSource.deleteHike(
            id = id
        )
    }

    override suspend fun getAllHikes(): Flow<List<Hike>> {
        return hikesDataSource.getAllHikes().map { it.map { HikesMapper().mapFromHikesDbToHike(it) } }
    }

    override suspend fun getHikeById(id: String): Flow<Hike> {
        return hikesDataSource.getHikeById(id).map { HikesMapper().mapFromHikesDbToHike(it) }
    }
}