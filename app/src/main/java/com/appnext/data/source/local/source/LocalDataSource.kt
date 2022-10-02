package com.appnext.data.source.local.source

import com.appnext.data.source.local.dao.AppNextDao
import com.appnext.model.database_entities.WeeklyDataEntity
import com.appnext.model.server_models.WeeklyDataModel


class LocalDataSource(private val appNextDao: AppNextDao) {

    suspend fun getWeeklyData() = appNextDao.getAllWeeklyData()

    suspend fun insertWeeklyData(weeklyDataModel: WeeklyDataModel) {
        appNextDao.whipWeeklyDataTable()
        weeklyDataModel.weeklyData.forEach { data ->
            appNextDao.insertWeeklyDataEntity(
                WeeklyDataEntity(
                    data.dailyData.dailyDistanceMeters,
                    data.dailyData.dailyKcal, data.dailyItem.dailyActivity, data.dailyItem.dailyGoal
                )
            )
        }
    }


}