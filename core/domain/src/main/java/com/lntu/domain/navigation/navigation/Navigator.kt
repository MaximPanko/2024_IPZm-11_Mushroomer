package com.lntu.domain.navigation.navigation

import androidx.navigation.NavHostController

interface Navigator {
    fun attach(_navHostController: NavHostController)
    fun detach()
    fun popBackStack()
}