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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lntu.presentation.components.MoreDialog
import com.lntu.screens.hike.HikesScreenUiState
import com.lntu.screens.mushroom.components.MushroomItem
import kotlinx.serialization.Serializable

object HikeDetailsScreenConstants {
    @Serializable
    data class Args(
        val id: String
    )
}


@Composable
fun HikeDetailsRoute(
    viewModel: HikeDetailsViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    HikeDetailsScreen(
        state = state,
        onBackClicked = viewModel::onBackClicked,
        onMushroomClicked = viewModel::onMushroomClicked,
        onAddMushroomClicked = viewModel::onAddMushroomClicked,
        onMushroomEditClicked = viewModel::onMushroomEditClicked,
        onMushroomDeleteClicked = viewModel::onMushroomDeleteClicked
    )
}

@Composable
internal fun HikeDetailsScreen(
    state: HikeDetailsUiState,
    onBackClicked: () -> Unit,
    onMushroomClicked: (String) -> Unit,
    onAddMushroomClicked: () -> Unit,
    onMushroomEditClicked: (String) -> Unit = {},
    onMushroomDeleteClicked: (String) -> Unit = {}
) {
    var moreDialogShown by remember { mutableStateOf<HikeDetailsUiState.MushroomUiState?>(null) }

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
                    MushroomItem(
                        mushroom = mushroom,
                        onMushroomClicked = onMushroomClicked,
                        onMoreClicked = { moreDialogShown = it },
                    )
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
        MoreDialog(
            show = moreDialogShown != null,
            onDismissRequest = { moreDialogShown = null },
            onEditClicked = {
                moreDialogShown?.let {
                    onMushroomEditClicked(it.id)
                    moreDialogShown = null
                }
            },
            onDeleteClicked = {
                moreDialogShown?.let {
                    onMushroomDeleteClicked(it.id)
                    moreDialogShown = null
                }
            }
        )
    }
}

@Composable
@Preview
fun HikeDetailsScreenPreview() {
    HikeDetailsScreen(
        state = HikeDetailsUiState(
            id = "1",
            mushrooms = listOf(
                HikeDetailsUiState.MushroomUiState(
                    hikeId = "1",
                    id = "1",
                    name = "name",
                    description = "description",
                    weight = 542.3
                )
            )
        ),
        onBackClicked = {},
        onMushroomClicked = {},
        onAddMushroomClicked = {},
        onMushroomEditClicked = {},
        onMushroomDeleteClicked = {}
    )
}