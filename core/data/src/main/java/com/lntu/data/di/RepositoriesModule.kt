package com.lntu.data.di

import com.lntu.data.hikes.HikesRepositoryImpl
import com.lntu.domain.hikes.HikesRepository
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
    internal abstract fun provideUserProfileRepository(impl: HikesRepositoryImpl): HikesRepository
}