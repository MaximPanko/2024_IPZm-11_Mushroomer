package com.lntu.domain.navigation

import androidx.navigation.NavHostController

interface Navigator {
    fun attach(_navHostController: NavHostController)
    fun detach()
    fun popBackStack()
    fun navigateToHikes()
    fun navigateToCreateHike()
    fun navigateToHikeDetails(id: String)
    fun navigateToCreateMushroom(id: String?, hikeId: String, name: String?, description: String?, weight: Double?)
    fun navigateToMushroomDetails(id: String)
    fun navigateToSettings()
    fun restartApp()
}