package com.appnext.service_locator

import com.appnext.data.repository.DashboardRepository
import org.koin.dsl.module


val repositoryModule = module {

    single { DashboardRepository(get(), get()) }
}