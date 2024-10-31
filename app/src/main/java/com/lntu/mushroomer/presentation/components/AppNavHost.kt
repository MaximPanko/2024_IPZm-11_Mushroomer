package com.lntu.mushroomer.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = com.lntu.test.testRoute
    ) {
        composable(com.lntu.test.testRoute) {
            com.lntu.test.TestRoute()
        }
    }
}