package com.appnext.service_locator

import com.appnext.data.repository.AppnextRepository
import org.koin.dsl.module


val repositoryModule = module {

    single { AppnextRepository(get(), get()) }
}