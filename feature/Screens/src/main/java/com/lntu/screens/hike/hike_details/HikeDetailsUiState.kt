package com.lntu.screens.hike.hike_details

data class HikeDetailsUiState(
    val id : String,
    val mushrooms : List<MushroomUiState>  = emptyList()
) {
    data class MushroomUiState(
        val hikeId: String,
        val id: String,
        val name: String,
        val description: String? = "Description",
        val weight: Double? = 543.2,
    )

    companion object {
        val DEFAULT = HikeDetailsUiState(
            id = "1",
            mushrooms = emptyList()
        )
    }
}