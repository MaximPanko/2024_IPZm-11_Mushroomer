package com.lntu.domain.navigation

import androidx.navigation.NavHostController

interface Navigator {
    fun attach(_navHostController: NavHostController)
    fun detach()
    fun popBackStack()
    fun navigateToHikes()
    fun navigateToCreateHike()
    fun navigateToHikeDetails(id: String)
}