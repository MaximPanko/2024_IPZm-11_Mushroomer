package com.lntu.screens.hike.hike_details

data class HikeDetailsUiState(
    val mushrooms : List<MushroomUiState>
) {
    data class MushroomUiState(
        val id: String,
        val name: String?,
        val description: String?,
        val weight: String?
    )

    companion object {
        val DEFAULT = HikeDetailsUiState(
            mushrooms = emptyList()
        )
    }
}