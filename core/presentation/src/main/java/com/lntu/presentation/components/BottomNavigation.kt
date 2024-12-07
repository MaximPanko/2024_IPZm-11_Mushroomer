package com.lntu.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun bottomNavigation(
    navHostController: NavHostController
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(
            onClick = { navHostController.navigate("hikes_screen_route") },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
            imageVector = Icons.Default.Home,
            contentDescription = "Home"
        ) }
        IconButton(
            onClick = { navHostController.navigate("create_hike") },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add hike"
        ) }
        IconButton(
            onClick = { navHostController.navigate("settings_screen_route") },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings"
            )
        }
    }
}