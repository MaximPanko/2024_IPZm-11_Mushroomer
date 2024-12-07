package com.lntu.screens.hike

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lntu.presentation.components.MoreDialog
import com.lntu.screens.hike.components.bottomNavigation
import com.lntu.screens.R
import com.lntu.screens.hike.components.HikeItem
import java.time.LocalDateTime
import java.time.Month

const val hikesScreenRoute = "hikes_screen_route"

@Composable
fun HikesRoute(
    navController: NavHostController,
    viewModel: HikesScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HikesScreen(
        state = state,
        navController = navController,
        createHike = viewModel::onCreateNewHikeClicked,
        onHikeClicked = viewModel::onHikeClicked,
        onEditClicked = viewModel::onHikeEditClicked,
        onDeleteClicked = viewModel::onDeleteHikeClicked
    )
}

@Composable
internal fun HikesScreen (
    state: HikesScreenUiState,
    navController : NavHostController,
    createHike: () -> Unit,
    onHikeClicked: (String) -> Unit = {},
    onEditClicked: (String) -> Unit = {},
    onDeleteClicked: (String) -> Unit = {},
) {
    var moreDialogShown by remember { mutableStateOf<HikesScreenUiState.HikeUiState?>(null) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            bottomNavigation(
                navHostController = navController
            )
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
                text = stringResource(id = R.string.welcome_message),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = stringResource(id = R.string.welcome_description),
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Button(
                onClick = { createHike() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF66BB6A))
            ) {
                Text(
                    text = stringResource(id = R.string.start_button),
                    color = Color.White,
                    fontSize = 18.sp
                )
            }

            Text(
                text = stringResource(id = R.string.my_routes),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                state.hikes.forEach { hike ->
                    HikeItem(
                        hike = hike,
                        onHikeClicked = onHikeClicked,
                        onMoreClicked = { moreDialogShown = it }
                    )
                }
            }

            MoreDialog(
                show = moreDialogShown != null,
                onDismissRequest = { moreDialogShown = null },
                onEditClicked = {
                    moreDialogShown?.let {
                        onEditClicked(it.id)
                        moreDialogShown = null
                    }
                },
                onDeleteClicked = {
                    moreDialogShown?.let {
                        onDeleteClicked(it.id)
                        moreDialogShown = null
                    }
                }
            )
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
        navController = rememberNavController(),
        createHike = { },
        onHikeClicked = { },
        onEditClicked = { },
        onDeleteClicked = { }
    )
}