package com.appnext.data.source.remote.api

import com.appnext.model.server_models.ServerErrorResponseModel
import com.appnext.model.server_models.WeeklyDataModel
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET

interface NetworkApi {

    @GET("nextandroid/daily_data")
    suspend fun getWeeklyData() : NetworkResponse<WeeklyDataModel, ServerErrorResponseModel>
}