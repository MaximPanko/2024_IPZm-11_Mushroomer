package com.lntu.domain.localization

interface LocalizationManager {

    fun changeLocale(appLanguage: AppLanguage)
    fun getAppLanguage(): AppLanguage
}