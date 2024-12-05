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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lntu.presentation.components.bottomNavigation
import com.lntu.screens.hike.components.HikeItem
import java.time.LocalDateTime
import java.time.Month

const val hikesScreenRoute = "hikes_screen_route"

@Composable
fun HikesRoute(
    viewModel: HikesScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HikesScreen(
        state = state,
        createHike = viewModel::onCreateNewHikeClicked
    )
}

@Composable
internal fun HikesScreen (
    state: HikesScreenUiState,
    createHike: () -> Unit,
    onHikeClicked: (String) -> Unit = {},
    onMoreClicked: (HikesScreenUiState.HikeUiState) -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { bottomNavigation() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Box {
                Button(
                    onClick = { createHike() },
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
                state.hikes.forEach { hike ->
                    HikeItem(hike, onHikeClicked, onMoreClicked)
                }
            }
        }
    }
}

@Preview
@Composable
internal fun HikesScreenPreview() {
    HikesScreen(
        state = HikesScreenUiState(
            hikes = listOf(
                HikesScreenUiState.HikeUiState(
                    id = "1",
                    name = "Hike name",
                    date = LocalDateTime.of(2021, Month.JANUARY, 1, 12, 0)
                )
            )
        ),
        createHike = { }
    )
}