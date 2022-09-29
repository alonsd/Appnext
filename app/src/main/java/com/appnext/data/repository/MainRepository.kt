package com.appnext.data.repository

import com.appnext.data.source.local.source.LocalDataSource
import com.appnext.data.source.remote.source.RemoteDataSource

class MainRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    suspend fun getWeeklyData() = remoteDataSource.getWeeklyData()

    suspend fun getDataFromLocal() = localDataSource.getAppNextModel()

}

