package com.lntu.screens.hike

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

const val hikesScreenRoute = "hikes_screen_route"

@Composable
fun HikesRoute(
    hikesScreenViewModel: HikesScreenViewModel = hiltViewModel()
) {
    HikesScreen(
        state = HikesScreenUiState.DEFAULT
    )
}

@Composable
internal fun HikesScreen (
    state: HikesScreenUiState,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Box {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add new hike")
                }
            }
            Text("My hikes")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

            }
        }
    }
}

@Preview
@Composable
internal fun HikesScreenPreview() {
    HikesScreen(
        state = HikesScreenUiState.DEFAULT,
    )
}