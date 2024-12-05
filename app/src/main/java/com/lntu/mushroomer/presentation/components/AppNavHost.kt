package com.lntu.mushroomer.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lntu.screens.hike.HikesRoute
import com.lntu.screens.hike.create_hike.CreateHikeRoute
import com.lntu.screens.hike.create_hike.createHikeRoute
import com.lntu.screens.hike.hikesScreenRoute

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
    }
}