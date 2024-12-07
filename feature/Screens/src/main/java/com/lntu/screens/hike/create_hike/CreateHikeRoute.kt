package com.lntu.screens.hike.create_hike

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.lntu.screens.R
import kotlinx.serialization.Serializable

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
        hikeNameChanged = viewModel::onHikeNameFieldChanged
    )
}

@Composable
internal fun CreateHikeScreen(
    state: CreateHikeUi,
    saveHikeClicked: () -> Unit,
    hikeNameChanged: (String) -> Unit
) {
    Scaffold { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = Color(0xFF66BB6A)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.add_trip),
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(id = R.string.enter_name),
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = state.name,
                            onValueChange = { hikeNameChanged(it) },
                            label = { Text(text = stringResource(id = R.string.name)) },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { saveHikeClicked() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF66AB6A))
                        ) {
                            Text(text = stringResource(id = R.string.Continue), color = Color.White)
                        }
                    }
                }
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
        hikeNameChanged = {}
    )
}