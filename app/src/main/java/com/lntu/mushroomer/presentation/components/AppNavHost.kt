package com.lntu.mushroomer.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lntu.screens.hike.HikesRoute
import com.lntu.screens.hike.create_hike.CreateHikeRoute
import com.lntu.screens.hike.create_hike.createHikeRoute
import com.lntu.screens.hike.hike_details.HikeDetailsRoute
import com.lntu.screens.hike.hike_details.hikeDetailsScreenRoute
import com.lntu.screens.hike.hikesScreenRoute
import com.lntu.screens.mushroom.create_mushroom.CreateMushroomRoute
import com.lntu.screens.mushroom.create_mushroom.createMushroomRoute

@Composable
fun AppNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = hikesScreenRoute
    ) {
        composable(hikesScreenRoute) {
            HikesRoute()
        }
        composable(createHikeRoute) {
            CreateHikeRoute()
        }
        composable(hikeDetailsScreenRoute) {
            HikeDetailsRoute()
        }
        composable(createMushroomRoute) {
            CreateMushroomRoute()
        }
    }
}