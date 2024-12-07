package com.lntu.data.localization

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import com.lntu.domain.localization.AppLanguage
import com.lntu.domain.localization.LocalizationManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class LocalizationManagerImpl @Inject constructor(
    @ApplicationContext val context: Context
) : LocalizationManager {

    override fun changeLocale(appLanguage: AppLanguage) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when (appLanguage) {
                AppLanguage.SYSTEM_DEFAULT -> {
                    context.getSystemService(LocaleManager::class.java).applicationLocales = LocaleList.getEmptyLocaleList()
                }

                else -> {
                    context.getSystemService(LocaleManager::class.java).applicationLocales = LocaleList.forLanguageTags(appLanguage.languageCode)
                }
            }
        } else {
            LocalizationManagerUnder33Api.storeAppLanguage(context, appLanguage)
        }
    }

    override fun getAppLanguage(): AppLanguage {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val localeManager = context.getSystemService(LocaleManager::class.java)
            if (localeManager.applicationLocales.isEmpty) return AppLanguage.SYSTEM_DEFAULT
            val languageCode = localeManager.applicationLocales.get(0).language
            return AppLanguage.entries.find { it.languageCode == languageCode } ?: DEFAULT_RESOURCE_LANGUAGE
        } else {
            return LocalizationManagerUnder33Api.getStoredAppLanguage(context)
        }
    }

    private companion object {
        val DEFAULT_RESOURCE_LANGUAGE = AppLanguage.ENGLISH
    }
}