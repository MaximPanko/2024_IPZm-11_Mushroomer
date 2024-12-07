package com.lntu.mushroomer.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lntu.screens.hike.HikesRoute
import com.lntu.screens.hike.create_hike.CreateHikeConstants
import com.lntu.screens.hike.create_hike.CreateHikeRoute
import com.lntu.screens.hike.hike_details.HikeDetailsRoute
import com.lntu.screens.hike.hike_details.HikeDetailsScreenConstants
import com.lntu.screens.hike.hikesScreenRoute
import com.lntu.screens.mushroom.create_mushroom.CreateMushroomConstants
import com.lntu.screens.mushroom.create_mushroom.CreateMushroomRoute
import com.lntu.screens.mushroom.mushroom_details.MushroomDetailsConstants
import com.lntu.screens.mushroom.mushroom_details.MushroomDetailsRoute
import com.lntu.screens.settings.SettingsScreenRoute
import com.lntu.screens.settings.settingsScreenRoute

@Composable
fun AppNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = hikesScreenRoute
    ) {
        composable(hikesScreenRoute) {
            HikesRoute(navController = navHostController)
        }
        composable<CreateHikeConstants.Args> {
            CreateHikeRoute()
        }
        composable<HikeDetailsScreenConstants.Args> {
            HikeDetailsRoute()
        }
        composable<CreateMushroomConstants.Args> {
            CreateMushroomRoute()
        }
        composable<MushroomDetailsConstants.Args> {
            MushroomDetailsRoute()
        }
        composable(settingsScreenRoute) {
            SettingsScreenRoute(navController = navHostController)
        }
    }
}