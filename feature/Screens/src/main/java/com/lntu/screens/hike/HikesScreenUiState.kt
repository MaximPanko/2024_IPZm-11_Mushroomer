package com.lntu.screens.hike

import java.time.LocalDateTime

data class HikesScreenUiState(
    val hikes : List<HikeUiState>
) {
    data class HikeUiState(
        val id: String,
        val name: String,
        val date: LocalDateTime
    )

    companion object {
        val DEFAULT = HikesScreenUiState(
            hikes = emptyList()
        )
    }
}