package com.lntu.screens.hike.create_hike

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun CreateHikeRoute(
    createHikeViewModel: CreateHikeViewModule = hiltViewModel()
) {
    CreateHikeScreen(
        state = CreateHikeUi.DEFAULT
    )
}

@Composable
internal fun CreateHikeScreen(
    state: CreateHikeUi
) {
    Scaffold { innerPadding ->
        Column {
            Box(Modifier.padding(innerPadding)) {
                Column {
                    Text("Create Hike")
                    TextField(
                        value = state.name,
                        onValueChange = { /*TODO*/ },
                    )
                }
            }
            Box(Modifier.padding(innerPadding)) {
                Row {
                    Button(
                        onClick = { /*TODO*/ }
                    ) { Text("Cancel") }
                    Button(
                        onClick = { /*TODO*/ }
                    ) { Text("Save") }
                }
            }
        }
    }
}

@Preview
@Composable
internal fun CreateHikePreview() {
    CreateHikeScreen(
        state = CreateHikeUi.DEFAULT
    )
}