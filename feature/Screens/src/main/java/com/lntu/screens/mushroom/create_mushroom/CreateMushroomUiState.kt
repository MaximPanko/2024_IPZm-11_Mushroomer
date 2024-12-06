package com.lntu.screens.mushroom.create_mushroom

data class CreateMushroomUiState(
    val hikeId: String = "" ,
    val id: String? = null,
    val name: String = "",
    val description: String = "",
    val weight: Double = 0.0
) {
    val isEditMode: Boolean
        get() = !id.isNullOrBlank()

    companion object{
        val DEFAULT = CreateMushroomUiState(
            hikeId = "0",
            id = null,
            name = "",
            description = "",
            weight = 0.0
        )
    }
}