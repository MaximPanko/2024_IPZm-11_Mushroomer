package com.lntu.screens.hike.hike_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lntu.screens.mushroom.MushroomItem

const val hikeDetailsScreenRoute = "hike_details_screen_route"

@Composable
fun HikeDetailsRoute(
    viewModel: HikeDetailsViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    HikeDetailsScreen(
        state = state,
        onBackClicked = viewModel::onBackClicked,
        onMushroomClicked = {},
        onAddMushroomClicked = {}
    )
}

@Composable
internal fun HikeDetailsScreen(
    state: HikeDetailsUiState,
    onBackClicked: () -> Unit,
    onMushroomClicked: (String) -> Unit,
    onAddMushroomClicked: () -> Unit,
    onMoreClicked: (HikeDetailsUiState.MushroomUiState) -> Unit = {}
) {
    Scaffold(
        topBar = {
            Row {
                IconButton(
                    onClick = { onBackClicked() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Icon(
                    modifier = Modifier.weight(1f),
                    imageVector = Icons.Default.Person,
                    contentDescription = "Logo"
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                state.mushrooms.forEach { mushroom ->
                    MushroomItem(mushroom, onMushroomClicked, onMoreClicked)
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                onClick = { onAddMushroomClicked() }
            ) {
                Text("Add new mushroom")
            }
        }
    }
}

@Composable
@Preview
fun HikeDetailsScreenPreview() {
    HikeDetailsScreen(
        state = HikeDetailsUiState(
            mushrooms = listOf(
                HikeDetailsUiState.MushroomUiState(
                    id = "1",
                    name = "name",
                    description = "description",
                    weight = "weight"
                )
            )
        ),
        onBackClicked = {},
        onMushroomClicked = {},
        onAddMushroomClicked = {}
    )
}