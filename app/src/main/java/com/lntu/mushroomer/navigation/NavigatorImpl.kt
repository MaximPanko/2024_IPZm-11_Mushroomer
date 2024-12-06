package com.lntu.mushroomer.navigation

import androidx.navigation.NavHostController
import com.lntu.domain.navigation.Navigator
import javax.inject.Inject

internal class NavigatorImpl @Inject constructor() : Navigator {

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
        navHostController?.navigate("create_hike")
    }

    override fun navigateToHikeDetails(id: String) {
        navHostController?.navigate("hike_details_screen_route")
    }
}