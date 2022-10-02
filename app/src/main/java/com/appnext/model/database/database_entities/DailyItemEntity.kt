package com.appnext.model.database.database_entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.appnext.utils.constants.Database.DAILY_DATA_TABLE
import com.appnext.utils.constants.Database.DAILY_ITEM_TABLE
import com.google.gson.annotations.SerializedName


@Entity(tableName = DAILY_ITEM_TABLE)
data class DailyItemEntity(
    val dailyActivity: Int,
    val dailyGoal: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}