package com.lntu.screens.mushroom.create_mushroom

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.serialization.Serializable

const val createMushroomRoute = "create_mushroom"

object CreateMushroomConstants {
    const val NAME_MUSHROOM_MAX_SYMBOLS = 50
    const val DESCRIPTION_MUSHROOM_MAX_SYMBOLS = 100

    @Serializable
    data class Args(
        val id: String? = null
    )
}

@Composable
fun CreateMushroomRoute(
    viewModel: CreateMushroomViewModule = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CreateMushroomScreen(
        state = state,
        onBackClicked = viewModel::onButtonBackClicked,
        mushroomNameChanged = viewModel::onMushroomNameFieldChanged,
        mushroomDescriptionChanged = viewModel::onMushroomDescriptionFieldChanged,
        mushroomWeightChanged = viewModel::onMushroomWeightFieldChanged,
        onAddButtonClicked = viewModel::onButtonAddClicked
    )
}

@Composable
internal fun CreateMushroomScreen(
    state: CreateMushroomUiState,
    onBackClicked: () -> Unit,
    mushroomNameChanged: (String) -> Unit,
    mushroomDescriptionChanged: (String) -> Unit,
    mushroomWeightChanged: (Double) -> Unit,
    onAddButtonClicked: () -> Unit
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
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
        ) {
            Column {
                Column {
                    Text("Enter name")
                    TextField(
                        value = state.name,
                        onValueChange = { mushroomNameChanged },
                    )
                }
                Column {
                    Text("Description")
                    TextField(
                        value = state.description,
                        onValueChange = { mushroomDescriptionChanged },
                    )
                }
                Column {
                    Text("Weight")
                    TextField(
                        value = state.weight.toString(),
                        onValueChange = { newValue ->
                            if (newValue.all { it.isDigit() }) {
                                mushroomWeightChanged(newValue.toDouble())
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                onClick = { onAddButtonClicked() }
            ) {
                Text("Add")
            }
        }
    }
}

@Composable
@Preview
fun PreviewCreateMushroomScreen() {
    CreateMushroomScreen(
        state = CreateMushroomUiState(),
        onBackClicked = {},
        mushroomNameChanged = {},
        mushroomDescriptionChanged = {},
        mushroomWeightChanged = {},
        onAddButtonClicked = {}
    )
}