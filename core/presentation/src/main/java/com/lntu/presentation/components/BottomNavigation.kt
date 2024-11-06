package com.lntu.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun bottomNavigation() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(
            onClick = { /* HOME */ }
        ) {  }
        IconButton(
            onClick = { /* CREATE HIKE */ }
        ) {  }
        IconButton(
            onClick = { /* SETTINGS */ }
        ) {  }
    }
}

@Composable
@Preview
fun BottomNavigationPreview() {
    bottomNavigation()
}