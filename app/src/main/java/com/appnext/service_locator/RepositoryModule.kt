package com.appnext.service_locator

import com.appnext.data.repository.MainRepository
import org.koin.dsl.module


val repositoryModule = module {

    single { MainRepository(get(), get()) }
}