package com.lntu.domain.navigation.hikes.entity

import java.time.LocalDateTime

data class Hike (
    val id: String,
    val name: String,
    val date: LocalDateTime
)