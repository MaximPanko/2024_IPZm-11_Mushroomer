package com.lntu.data.di

import com.lntu.data.hikes.entity.HikesDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RealmModule {

    @Singleton
    @Provides
    fun provideRealm(): Realm {
        return Realm.open(
            RealmConfiguration.Builder(dbEntities)
                .build()
        )
    }
}

val dbEntities = setOf(
    HikesDb::class
)