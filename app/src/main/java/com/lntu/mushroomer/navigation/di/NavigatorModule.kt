package com.lntu.mushroomer.navigation.di

import com.lntu.domain.navigation.Navigator
import com.lntu.mushroomer.navigation.NavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigatorModule {

    @Singleton
    @Binds
    internal abstract fun provideNavigator(impl: NavigatorImpl): Navigator

}