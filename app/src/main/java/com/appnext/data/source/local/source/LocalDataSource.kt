package com.appnext.data.source.local.source

import com.appnext.data.source.local.dao.AppNextDao
import com.appnext.model.database.database_entities.DailyDataEntity
import com.appnext.model.database.database_entities.DailyItemEntity
import com.appnext.model.database.database_response.WeeklyDataCombined
import com.appnext.model.server_models.WeeklyDataModel


class LocalDataSource(private val appNextDao: AppNextDao) {

    suspend fun getWeeklyData(): List<WeeklyDataCombined> = appNextDao.getWeeklyData()

    suspend fun insertWeeklyData(weeklyDataModel: WeeklyDataModel) {
        weeklyDataModel.weeklyData.forEach { data ->
            val dailyData = data.dailyData
            val dailyItem = data.dailyItem
            appNextDao.insertDailyDataEntity(DailyDataEntity(dailyDistanceMeters = dailyData.dailyDistanceMeters, dailyKcal = dailyData.dailyKcal))
            appNextDao.insertDailyItemEntity(DailyItemEntity(dailyActivity = dailyItem.dailyActivity, dailyGoal = dailyItem.dailyGoal))
        }
    }


}