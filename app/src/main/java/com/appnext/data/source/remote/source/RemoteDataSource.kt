package com.appnext.data.source.remote.source

import com.appnext.data.source.remote.api.NetworkApi

class RemoteDataSource(private val networkApi: NetworkApi) {

    suspend fun getWeeklyData() = networkApi.getWeeklyData()


}