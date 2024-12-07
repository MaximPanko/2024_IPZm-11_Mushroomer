package com.lntu.screens.mushroom.create_mushroom

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lntu.screens.R
import kotlinx.serialization.Serializable

object CreateMushroomConstants {
    const val NAME_MUSHROOM_MAX_SYMBOLS = 50
    const val DESCRIPTION_MUSHROOM_MAX_SYMBOLS = 100

    @Serializable
    data class Args(
        val id: String? = null,
        val hikeId: String,
        val name: String? = null,
        val description: String? = null,
        val weight: Double? = null
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.enter_name),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            TextField(
                value = state.name,
                onValueChange = { mushroomNameChanged(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                    .padding(8.dp),
                placeholder = { Text(text = stringResource(id = R.string.name)) },
                colors = TextFieldDefaults.colors(Color.Black)
            )
            OutlinedTextField(
                value = state.description,
                onValueChange = { mushroomDescriptionChanged(it) },
                label = { Text(text = stringResource(id = R.string.description)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = TextFieldDefaults.colors(Color.Black)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.weight),
                    fontSize = 16.sp,
                    modifier = Modifier.width(80.dp)
                )
                TextField(
                    value = state.weight.toString(),
                    onValueChange = {
                        val newWeight = it.toDoubleOrNull() ?: 0.0
                        mushroomWeightChanged(newWeight)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.colors(Color.Black),
                    trailingIcon = { Text(text = ".g") }
                )
            }
            Button(
                onClick = { onAddButtonClicked() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF66BB6A))
            ) {
                Text(
                    text = stringResource(id = R.string.add),
                    color = Color.White,
                    fontSize = 18.sp
                )
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