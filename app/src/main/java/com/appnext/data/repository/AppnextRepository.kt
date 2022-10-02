package com.appnext.data.repository

import com.appnext.R
import com.appnext.data.source.local.source.LocalDataSource
import com.appnext.data.source.remote.source.RemoteDataSource
import com.appnext.model.database.database_response.WeeklyDataCombined
import com.appnext.model.ui_models.TimelineListItem
import com.appnext.model.ui_models.WeeklyProgressListItem
import com.appnext.utils.application.App
import com.appnext.utils.constants.TimeConstants.TWELVE_HOURS_IN_MILLIS
import com.appnext.utils.extensions.capitaliseFullyUpperCasedString
import com.appnext.utils.extensions.lastTimeDataFetch
import com.appnext.utils.extensions.sharedPreferences
import com.haroldadmin.cnradapter.NetworkResponse
import java.time.LocalDateTime

class AppnextRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    suspend fun getWeeklyProgressItems(): NetworkResponse<MutableList<WeeklyProgressListItem>, String> {
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

    suspend fun getTimelineItems(): NetworkResponse<MutableList<TimelineListItem>, String> {
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
        var timelineListItems = mutableListOf<TimelineListItem>()
        (weeklyData as NetworkResponse.Success).body.forEach { data ->
            timelineListItems = mutableListOf(
                TimelineListItem(
                    sixDaysAgo.dayOfWeek.value.toString(),
                    sixDaysAgo.dayOfWeek.name.substring(0..2)
                        .capitaliseFullyUpperCasedString(),
                    if (data.dailyActivity >= data.dailyGoal) R.color.green else R.color.blue,
                    data.dailyDistanceMeters.toString(), data.dailyKcal.toString(),
                    false
                ),
                TimelineListItem(
                    fiveDaysAgo.dayOfWeek.value.toString(),
                    fiveDaysAgo.dayOfWeek.name.substring(0..2)
                        .capitaliseFullyUpperCasedString(),
                    if (data.dailyActivity >= data.dailyGoal) R.color.green else R.color.blue,
                    data.dailyDistanceMeters.toString(), data.dailyKcal.toString(),
                    false
                ),
                TimelineListItem(
                    fourDaysAgo.dayOfWeek.value.toString(),
                    fourDaysAgo.dayOfWeek.name.substring(0..2)
                        .capitaliseFullyUpperCasedString(),
                    if (data.dailyActivity >= data.dailyGoal) R.color.green else R.color.blue,
                    data.dailyDistanceMeters.toString(), data.dailyKcal.toString(),
                    false
                ),
                TimelineListItem(
                    threeDaysAgo.dayOfWeek.value.toString(),
                    threeDaysAgo.dayOfWeek.name.substring(0..2)
                        .capitaliseFullyUpperCasedString(),
                    if (data.dailyActivity >= data.dailyGoal) R.color.green else R.color.blue,
                    data.dailyDistanceMeters.toString(), data.dailyKcal.toString(),
                    false
                ),
                TimelineListItem(
                    twoDaysAgo.dayOfWeek.value.toString(),
                    twoDaysAgo.dayOfWeek.name.substring(0..2)
                        .capitaliseFullyUpperCasedString(),
                    if (data.dailyActivity >= data.dailyGoal) R.color.green else R.color.blue,
                    data.dailyDistanceMeters.toString(), data.dailyKcal.toString(),
                    false
                ),
                TimelineListItem(
                    yesterday.dayOfWeek.value.toString(),
                    yesterday.dayOfWeek.name.substring(0..2)
                        .capitaliseFullyUpperCasedString(),
                    if (data.dailyActivity >= data.dailyGoal) R.color.green else R.color.blue,
                    data.dailyDistanceMeters.toString(), data.dailyKcal.toString(),
                    true
                ),
                TimelineListItem(
                    today.dayOfWeek.value.toString(),
                    today.dayOfWeek.name.substring(0..2)
                        .capitaliseFullyUpperCasedString(),
                    if (data.dailyActivity >= data.dailyGoal) R.color.green else R.color.blue,
                    data.dailyDistanceMeters.toString(), data.dailyKcal.toString(),
                    true
                )
            )
        }
        return NetworkResponse.Success(timelineListItems, code = 200)
    }


    private suspend fun getWeeklyData(): NetworkResponse<List<WeeklyDataCombined>, String> {
        val currentTimeMillis = System.currentTimeMillis()
        if (sharedPreferences.lastTimeDataFetch - currentTimeMillis >= TWELVE_HOURS_IN_MILLIS) {
            val weeklyData = remoteDataSource.getWeeklyData()
            if (weeklyData is NetworkResponse.Error) return NetworkResponse.ServerError(
                body = App.applicationContext()
                    .getString(R.string.dashboard_repository_general_error), code = 400
            )
            sharedPreferences.lastTimeDataFetch = currentTimeMillis
            localDataSource.insertWeeklyData((weeklyData as NetworkResponse.Success).body)
        }
        return NetworkResponse.Success(localDataSource.getWeeklyData(), code = 200)
    }

}

