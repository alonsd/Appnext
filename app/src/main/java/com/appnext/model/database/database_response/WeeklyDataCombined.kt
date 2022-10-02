package com.appnext.model.database.database_response

data class WeeklyDataCombined(
    val dailyDistanceMeters: Int,
    val dailyKcal: Int,
    val dailyActivity: Int,
    val dailyGoal: Int
)
