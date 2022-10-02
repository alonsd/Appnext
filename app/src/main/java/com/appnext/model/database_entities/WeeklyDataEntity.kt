package com.appnext.model.database_entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeeklyDataEntity(
    val dailyDistanceMeters: Int,
    val dailyKcal: Int,
    val dailyActivity: Int,
    val dailyGoal: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}
