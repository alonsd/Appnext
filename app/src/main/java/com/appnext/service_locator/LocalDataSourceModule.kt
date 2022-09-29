package com.appnext.service_locator

import com.appnext.data.source.local.source.LocalDataSource
import com.appnext.database.AppNextDatabase
import org.koin.dsl.module

val localDataSourceModule = module {

    single { LocalDataSource(AppNextDatabase.getInstance().getAppNextDao()) }

}