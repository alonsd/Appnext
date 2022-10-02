package com.appnext.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.appnext.model.database_entities.WeeklyDataEntity

@Dao
interface AppNextDao {

    @Query("select * from WeeklyDataEntity")
    suspend fun getAllWeeklyData() : List<WeeklyDataEntity>

    @Query("delete from WeeklyDataEntity")
    suspend fun whipWeeklyDataTable()

    @Insert
    suspend fun insertWeeklyDataEntity(entity : WeeklyDataEntity)
}