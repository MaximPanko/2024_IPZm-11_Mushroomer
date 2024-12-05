package com.lntu.screens.hike.create_hike

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.serialization.Serializable

const val createHikeRoute = "create_hike"

object CreateHikeConstants {
    const val NAME_HIKE_MAX_SYMBOLS = 50

    @Serializable
    data class Args(
        val id: String? = null
    )
}

@Composable
fun CreateHikeRoute(
    viewModel: CreateHikeViewModule = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CreateHikeScreen(
        state = state,
        saveHikeClicked = viewModel::onButtonSaveClicked,
        cancelClicked = viewModel::onButtonCancelClicked,
        hikeNameChanged = viewModel::onHikeNameFieldChanged
    )
}

@Composable
internal fun CreateHikeScreen(
    state: CreateHikeUi,
    saveHikeClicked: () -> Unit,
    cancelClicked: () -> Unit,
    hikeNameChanged: (String) -> Unit
) {
    Scaffold { innerPadding ->
        Column(Modifier
            .padding(innerPadding)
            .fillMaxSize()
        ) {
            Column {
                Text("Create Hike")
                TextField(
                    value = state.name,
                    onValueChange = { hikeNameChanged(it) },
                )
            }
            Spacer(Modifier.weight(1f))
            Row(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                Button(
                    onClick = { cancelClicked() },
                    Modifier
                        .weight(1f),
                ) { Text("Cancel") }
                Button(
                    onClick = { saveHikeClicked() },
                    Modifier
                        .weight(1f),
                ) { Text("Save") }
            }
        }
    }
}

@Preview
@Composable
internal fun CreateHikePreview() {
    CreateHikeScreen(
        state = CreateHikeUi.DEFAULT,
        saveHikeClicked = {},
        cancelClicked = {},
        hikeNameChanged = {}
    )
}