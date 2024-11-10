package com.lntu.data.hikes.dataSource

import com.lntu.data.hikes.entity.HikesDb
import com.lntu.domain.navigation.hikes.entity.Hike
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class HikesMapper {

    fun mapFromHikesDbToHikes(hikesDb: List<HikesDb>): List<Hike> {
        return hikesDb.map { mapFromHikesDbToHike(it) }
    }

    fun mapFromHikesDbToHike(hikesDb: HikesDb): Hike {
        return Hike(
            id = hikesDb.id.toHexString(),
            name = hikesDb.name,
            date = LocalDateTime.ofInstant(Instant.ofEpochMilli(hikesDb.date), ZoneId.systemDefault()) // Converting from Long to LocalDateTime
        )
    }
}