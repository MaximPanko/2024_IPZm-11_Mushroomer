package com.lntu.screens.settings

import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lntu.domain.localization.AppLanguage
import com.lntu.domain.localization.LocalizationManager
import com.lntu.domain.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private var navigator: Navigator,
    private val localizationManager: LocalizationManager
) : ViewModel() {
    private val _state = MutableStateFlow(SettingsScreenUiState.DEFAULT)
    internal val state: StateFlow<SettingsScreenUiState>
        get() = _state

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedAppLanguage = localizationManager.getAppLanguage()
                )
            }
        }
    }

    fun onBackClicked() {
        navigator.popBackStack()
    }

    fun onLanguageClicked(appLanguage: AppLanguage) {
        if (appLanguage == state.value.selectedAppLanguage) return
        viewModelScope.launch {
            delay(100)
            localizationManager.changeLocale(appLanguage)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                _state.update {
                    it.copy(
                        selectedAppLanguage = localizationManager.getAppLanguage()
                    )
                }
            } else {
                navigator.restartApp()
            }
        }
    }

}