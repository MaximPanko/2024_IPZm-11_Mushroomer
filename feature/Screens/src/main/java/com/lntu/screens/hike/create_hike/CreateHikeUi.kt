package com.lntu.screens.hike.create_hike

data class CreateHikeUi(
    val id: String? = null,
    val name: String = ""
) {
    val isEditMode: Boolean
        get() = !id.isNullOrBlank()

    companion object{
        val DEFAULT = CreateHikeUi(
            id = null,
            name = ""
        )
    }
}