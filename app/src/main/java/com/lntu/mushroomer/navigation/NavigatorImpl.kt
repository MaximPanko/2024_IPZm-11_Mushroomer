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

    override fun navigateToCreateHike(id: String) {
        navHostController?.navigate("create_hike")
    }
}