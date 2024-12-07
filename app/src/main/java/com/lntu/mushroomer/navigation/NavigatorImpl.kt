package com.lntu.mushroomer.navigation

import android.app.Activity
import androidx.navigation.NavHostController
import com.lntu.domain.navigation.Navigator
import com.lntu.screens.hike.create_hike.CreateHikeConstants
import com.lntu.screens.hike.hike_details.HikeDetailsScreenConstants
import com.lntu.screens.mushroom.create_mushroom.CreateMushroomConstants
import com.lntu.screens.mushroom.mushroom_details.MushroomDetailsConstants
import javax.inject.Inject

internal class NavigatorImpl @Inject constructor() : Navigator {

    private var activity: Activity? = null

    var navHostController: NavHostController? = null

    override fun attach(_navHostController: NavHostController) {
        navHostController = _navHostController
    }

    override fun detach() {
        navHostController = null
    }

    override fun popBackStack() {
        navHostController?.popBackStack()
    }

    override fun navigateToHikes() {
        navHostController?.navigate("hikes_screen_route")
    }

    override fun navigateToCreateHike() {
        navHostController?.navigate(CreateHikeConstants.Args())
    }

    override fun navigateToHikeEdit(id: String) {
        navHostController?.navigate(CreateHikeConstants.Args(id))
    }

    override fun navigateToHikeDetails(id: String) {
        navHostController?.navigate(HikeDetailsScreenConstants.Args(id))
    }

    override fun navigateToCreateMushroom(
        id: String?,
        hikeId: String,
        name: String?,
        description: String?,
        weight: Double?
    ) {
        navHostController?.navigate(
            CreateMushroomConstants.Args(
                id = id,
                hikeId = hikeId,
                name = name,
                description = description,
                weight = weight
            )
        )
    }

    override fun navigateToMushroomDetails(id: String) {
        navHostController?.navigate(MushroomDetailsConstants.Args(id)) {
            popUpTo <MushroomDetailsConstants.Args>()
        }
    }

    override fun navigateToSettings() {
        navHostController?.navigate("settings_screen_route")
    }

    override fun restartApp() {
        activity?.recreate()
        navHostController?.navigate("hikes_screen_route") {
            navHostController?.graph?.id?.let { id ->
                popUpTo(id) {
                    inclusive = true
                }
            }
        }
    }

}