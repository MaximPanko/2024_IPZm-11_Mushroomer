package com.lntu.domain.coroutines

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class AppScope

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher