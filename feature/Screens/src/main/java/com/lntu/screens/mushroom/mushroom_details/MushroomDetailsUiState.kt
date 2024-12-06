package com.lntu.screens.mushroom.mushroom_details

data class MushroomDetailsUiState(
    val id : String? = "",
    val name : String = "",
    val description : String? = "",
    val weight : Double? = 0.0
) {
    companion object {
        val DEFAULT = MushroomDetailsUiState(
            id = "1",
            name = "Name",
            description = "Description",
            weight = 543.2
        )
    }
}