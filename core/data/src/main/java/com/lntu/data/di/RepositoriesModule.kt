package com.lntu.data.di

import com.lntu.data.hikes.HikesRepositoryImpl
import com.lntu.data.localization.LocalizationManagerImpl
import com.lntu.data.mushrooms.MushroomesRepositoryImpl
import com.lntu.domain.hikes.HikesRepository
import com.lntu.domain.localization.LocalizationManager
import com.lntu.domain.mushrooms.MushroomsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Singleton
    @Binds
    internal abstract fun provideHikesRepository(impl: HikesRepositoryImpl): HikesRepository
    
    @Singleton
    @Binds
    internal abstract fun provideMushroomsRepository(impl: MushroomesRepositoryImpl): MushroomsRepository

    @Singleton
    @Binds
    internal abstract fun provideLocalizationManager(impl: LocalizationManagerImpl): LocalizationManager
}