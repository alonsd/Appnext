package com.appnext.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.appnext.model.database.database_entities.DailyDataEntity
import com.appnext.model.database.database_entities.DailyItemEntity
import com.appnext.model.database.database_response.WeeklyDataCombined

@Dao
interface AppNextDao {

    @Query("""select * from (select dailyDistanceMeters, dailyKcal from DailyData), (select dailyActivity, dailyGoal from DailyItem)""")
    suspend fun getWeeklyData() : List<WeeklyDataCombined>

    @Insert
    suspend fun insertDailyDataEntity(entity : DailyDataEntity)

    @Insert
    suspend fun insertDailyItemEntity(entity : DailyItemEntity)
}