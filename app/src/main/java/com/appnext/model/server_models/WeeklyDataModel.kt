package com.appnext.model.server_models


import com.google.gson.annotations.SerializedName

data class WeeklyDataModel(
    @SerializedName("weekly_data")
    val weeklyData: List<WeeklyData>
) {
    data class WeeklyData(
        @SerializedName("daily_data")
        val dailyData: DailyData,
        @SerializedName("daily_item")
        val dailyItem: DailyItem
    ) {
        data class DailyData(
            @SerializedName("daily_distance_meters")
            val dailyDistanceMeters: Int,
            @SerializedName("daily_kcal")
            val dailyKcal: Int
        )

        data class DailyItem(
            @SerializedName("daily_activity")
            val dailyActivity: Int,
            @SerializedName("daily_goal")
            val dailyGoal: Int
        )
    }
}