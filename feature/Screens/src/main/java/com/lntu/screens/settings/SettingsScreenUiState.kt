package com.lntu.screens.settings

import com.lntu.domain.localization.AppLanguage

data class SettingsScreenUiState(
    val selectedAppLanguage: AppLanguage?,
    val languages: List<AppLanguage>
    ) {

        companion object {
            val DEFAULT = SettingsScreenUiState(
                selectedAppLanguage = null,
                languages = AppLanguage.entries
            )
        }
}