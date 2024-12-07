package com.lntu.screens.hike.hike_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lntu.presentation.components.MoreDialog
import com.lntu.screens.R
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

@OptIn(ExperimentalMaterial3Api::class)
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
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.mushrooms),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBackClicked() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF2E7D32)
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
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

                Button(
                    onClick = { onAddMushroomClicked() },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF66AB6A))
                ) {
                    Text(
                        text = stringResource(id = R.string.add_mushroom),
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                }
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