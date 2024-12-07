package com.lntu.data.localization

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.res.Configuration
import android.os.Build
import com.lntu.domain.localization.AppLanguage
import java.util.Locale

object LocalizationManagerUnder33Api {

    private const val SHARED_PREF_NAME = "app_language_preferences_under_33_api"
    private const val KEY_LANGUAGE = "app_language"

    fun wrapContext(newBase: Context): Context {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) return newBase
        val storedAppLanguage = getStoredAppLanguage(newBase)
        if (storedAppLanguage == AppLanguage.SYSTEM_DEFAULT) return newBase
        val locale = Locale(storedAppLanguage.languageCode)
        Locale.setDefault(locale)
        val newConfig = Configuration(newBase.resources.configuration)
        newConfig.setLocale(locale)
        return newBase.createConfigurationContext(newConfig)
    }

    fun storeAppLanguage(context: Context, appLanguage: AppLanguage) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)
        sharedPreferences.edit()
            .putString(KEY_LANGUAGE, if (appLanguage == AppLanguage.SYSTEM_DEFAULT) null else appLanguage.name)
            .apply()
    }

    fun getStoredAppLanguage(context: Context): AppLanguage {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)
        return sharedPreferences.getString(KEY_LANGUAGE, null)?.let { AppLanguage.valueOf(it) } ?: AppLanguage.SYSTEM_DEFAULT
    }
}