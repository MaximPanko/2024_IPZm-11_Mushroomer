package com.lntu.domain.localization

import androidx.annotation.StringRes
import com.lntu.domain.R

enum class AppLanguage(
    val languageCode: String,
    @StringRes val stringResId: Int
) {

    UKRAINE("uk", R.string.app_language_ukraine),
    ENGLISH("en", R.string.app_language_english),
    SYSTEM_DEFAULT("", R.string.app_language_system_default)
}