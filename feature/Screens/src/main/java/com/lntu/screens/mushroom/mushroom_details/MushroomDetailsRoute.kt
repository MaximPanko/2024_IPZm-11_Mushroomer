package com.lntu.screens.mushroom.mushroom_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.serialization.Serializable

object MushroomDetailsConstants {
    @Serializable
    data class Args(
        val id: String? = null
    )
}

@Composable
fun MushroomDetailsRoute(
    viewModel: MushroomDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MushroomDetailsScreen(
        state = state,
        onBackClicked = viewModel::onBackClicked,
    )
}

@Composable
fun MushroomDetailsScreen(
    state: MushroomDetailsUiState,
    onBackClicked: () -> Unit,
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
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column {
                Text("Mushroom name: ${state.name}")
                Text("Mushroom description: ${state.description}")
                Text("Mushroom weight: ${state.weight}")
            }
        }
    }
}

@Composable
@Preview
fun MushroomDetailsScreenPreview() {
    MushroomDetailsScreen(
        state = MushroomDetailsUiState.DEFAULT,
        onBackClicked = {}
    )
}