package com.lntu.screens.hike.hike_details

data class HikeDetailsUiState(
    val mushrooms : List<MushroomUiState>
) {
    data class MushroomUiState(
        val id: String,
        val name: String,
        val description: String? = "Description",
        val weight: Double? = 543.2,
    )

    companion object {
        val DEFAULT = HikeDetailsUiState(
            mushrooms = emptyList()
        )
    }
}