package com.appnext.service_locator

import com.appnext.data.source.remote.source.RemoteDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single { RemoteDataSource(get()) }
}