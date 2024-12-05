package com.lntu.domain.hikes

import java.time.LocalDateTime

data class Hike (
    val id: String,
    val name: String,
    val date: LocalDateTime
)