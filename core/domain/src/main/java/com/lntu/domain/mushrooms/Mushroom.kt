package com.lntu.domain.mushrooms

data class Mushroom (
    val hikeId: String,
    val id: String,
    val name: String,
    val description: String? = "Description",
    val weight: Double? = 54.4,
)