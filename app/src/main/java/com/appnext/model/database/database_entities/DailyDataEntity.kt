package com.appnext.model.database.database_entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.appnext.utils.constants.Database.DAILY_DATA_TABLE


@Entity(tableName = DAILY_DATA_TABLE)
data class DailyDataEntity(
    val dailyDistanceMeters: Int,
    val dailyKcal: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}