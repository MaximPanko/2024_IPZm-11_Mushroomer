package com.lntu.screens.settings

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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lntu.domain.localization.AppLanguage
import com.lntu.presentation.components.bottomNavigation

const val settingsScreenRoute = "settings_screen_route"

@Composable
fun SettingsScreenRoute(
    navController: NavHostController,
    viewModel: SettingsScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SettingsScreen(
        state = state,
        navController = navController,
        onBackClicked = viewModel::onBackClicked,
        onLanguageClicked = viewModel::onLanguageClicked
    )
}

@Composable
fun SettingsScreen(
    state: SettingsScreenUiState,
    navController : NavHostController,
    onBackClicked: () -> Unit,
    onLanguageClicked: (AppLanguage) -> Unit
) {
    Scaffold(
        bottomBar = { bottomNavigation(
            navHostController = navController
        ) },
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
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text("Settings")
                var isLanguageDropDownShown by remember { mutableStateOf(false) }

                Box(modifier = Modifier.fillMaxWidth()) {
                    Button( onClick = { isLanguageDropDownShown = true }) {
                        Text(stringResource(id = state.selectedAppLanguage?.stringResId ?: AppLanguage.ENGLISH.stringResId))
                    }
                    DropdownMenu(
                        expanded = isLanguageDropDownShown,
                        onDismissRequest = { isLanguageDropDownShown = false }
                    ) {
                        state.languages.forEach { language ->
                            DropdownMenuItem(
                                text = { Text(stringResource(id = language.stringResId)) },
                                onClick = {
                                    isLanguageDropDownShown = false
                                    onLanguageClicked(language)
                                },
                            )
                        }
                    }
                }
        }
    }
}

@Composable
@Preview
fun SettingsScreenPreview() {
    SettingsScreen(
        state = SettingsScreenUiState(
            selectedAppLanguage = null,
            languages = AppLanguage.entries
        ),
        navController = rememberNavController(),
        onBackClicked = {},
        onLanguageClicked = {}
    )
}