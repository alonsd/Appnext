package com.appnext.data.repository

import android.net.Network
import com.appnext.R
import com.appnext.data.source.local.source.LocalDataSource
import com.appnext.data.source.remote.source.RemoteDataSource
import com.appnext.model.database.database_response.WeeklyDataCombined
import com.appnext.model.server_models.WeeklyDataModel
import com.appnext.model.ui_models.WeeklyProgressListItem
import com.appnext.utils.application.App
import com.appnext.utils.extensions.capitaliseFullyUpperCasedString
import com.haroldadmin.cnradapter.NetworkResponse
import java.time.LocalDateTime

class AppnextRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    suspend fun getWeeklyProgressListItem(): NetworkResponse<MutableList<WeeklyProgressListItem>, String> {
        val weeklyData = getWeeklyData()
        if (weeklyData is NetworkResponse.Error) return NetworkResponse.ServerError(
            body = App.applicationContext()
                .getString(R.string.dashboard_repository_general_error), code = 400
        )
        val today = LocalDateTime.now()
        val yesterday = LocalDateTime.now().minusDays(1)
        val twoDaysAgo = LocalDateTime.now().minusDays(2)
        val threeDaysAgo = LocalDateTime.now().minusDays(3)
        val fourDaysAgo = LocalDateTime.now().minusDays(4)
        val fiveDaysAgo = LocalDateTime.now().minusDays(5)
        val sixDaysAgo = LocalDateTime.now().minusDays(6)
        var weeklyProgressListItems = mutableListOf<WeeklyProgressListItem>()
        (weeklyData as NetworkResponse.Success).body.forEach { data ->
            weeklyProgressListItems = mutableListOf(
                WeeklyProgressListItem(
                    sixDaysAgo.dayOfWeek.name.substring(0..2)
                        .capitaliseFullyUpperCasedString(),
                    data.dailyActivity, data.dailyGoal, false
                ),
                WeeklyProgressListItem(
                    fiveDaysAgo.dayOfWeek.name.substring(0..2)
                        .capitaliseFullyUpperCasedString(),
                    data.dailyActivity, data.dailyGoal, false
                ),
                WeeklyProgressListItem(
                    fourDaysAgo.dayOfWeek.name.substring(0..2)
                        .capitaliseFullyUpperCasedString(),
                    data.dailyActivity, data.dailyGoal, false
                ),
                WeeklyProgressListItem(
                    threeDaysAgo.dayOfWeek.name.substring(0..2)
                        .capitaliseFullyUpperCasedString(),
                    data.dailyActivity, data.dailyGoal, false
                ),
                WeeklyProgressListItem(
                    twoDaysAgo.dayOfWeek.name.substring(0..2)
                        .capitaliseFullyUpperCasedString(),
                    data.dailyActivity, data.dailyGoal, false
                ),
                WeeklyProgressListItem(
                    yesterday.dayOfWeek.name.substring(0..2)
                        .capitaliseFullyUpperCasedString(),
                    data.dailyActivity, data.dailyGoal, false
                ),
                WeeklyProgressListItem(
                    today.dayOfWeek.name.substring(0..2)
                        .capitaliseFullyUpperCasedString(),
                    data.dailyActivity, data.dailyGoal, true
                ),
            )
        }
        return NetworkResponse.Success(weeklyProgressListItems, code = 200)
    }


    private suspend fun getWeeklyData(): NetworkResponse<List<WeeklyDataCombined>, String> {
        val currentTimeMillis = System.currentTimeMillis()
        val weeklyData = remoteDataSource.getWeeklyData()
        if (weeklyData is NetworkResponse.Error) return NetworkResponse.ServerError(
            body = App.applicationContext()
                .getString(R.string.dashboard_repository_general_error), code = 400
        )
        localDataSource.insertWeeklyData((weeklyData as NetworkResponse.Success).body)
        return NetworkResponse.Success(localDataSource.getWeeklyData(), code = 200)
//        if (sharedPreferences.lastTimeDataFetch - currentTimeMillis >= TWELVE_HOURS_IN_MILLIS){
//
//        } else {
//
//        }
    }

}

