package com.lntu.presentation.components

import androidx.compose.foundation.background
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
            onClick = { /* HOME */ },
            modifier = Modifier.weight(1f)
                .background(androidx.compose.ui.graphics.Color.Blue)
        ) {  }
        IconButton(
            onClick = { /* CREATE HIKE */ },
            modifier = Modifier.weight(1f)
                .background(androidx.compose.ui.graphics.Color.Green)
        ) {  }
        IconButton(
            onClick = { /* SETTINGS */ },
            modifier = Modifier.weight(1f)
                .background(androidx.compose.ui.graphics.Color.Red)
        ) {  }
    }
}

@Composable
@Preview
fun BottomNavigationPreview() {
    bottomNavigation()
}