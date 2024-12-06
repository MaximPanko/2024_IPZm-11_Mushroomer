package com.lntu.data.mushrooms.dataSource

import com.lntu.data.mushrooms.entity.MushroomesDb
import com.lntu.domain.mushrooms.Mushroom

class MushroomesMapper {
    fun mapFromMushroomsDbToMushrooms(mushroomesDb: List<MushroomesDb>): List<Mushroom> {
        return mushroomesDb.map { mapFromMushroomsDbToMushroom(it) }
    }

    fun mapFromMushroomsDbToMushroom(mushroomesDb: MushroomesDb): Mushroom {
        return Mushroom(
            id = mushroomesDb.id.toHexString(),
            hikeId = mushroomesDb.hikeId,
            name = mushroomesDb.name,
            description = mushroomesDb.description,
            weight = mushroomesDb.weight
        )
    }
}
