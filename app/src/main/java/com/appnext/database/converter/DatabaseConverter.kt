package com.appnext.database.converter

import androidx.room.TypeConverter
import com.appnext.model.server_models.WeeklyDataModel
import com.google.gson.Gson


class DatabaseConverter {

    private val gson = Gson()

    @TypeConverter
    fun weeklyDataToString(entity: WeeklyDataModel.WeeklyData): String {
        return gson.toJson(entity)
    }

    @TypeConverter
    fun stringToWeeklyData(string: String): WeeklyDataModel.WeeklyData {
        return gson.fromJson(string, WeeklyDataModel.WeeklyData::class.java)
    }

    @TypeConverter
    fun dailyDataToString(entity: WeeklyDataModel.WeeklyData.DailyData): String {
        return gson.toJson(entity)
    }

    @TypeConverter
    fun stringToDailyData(string: String): WeeklyDataModel.WeeklyData.DailyData {
        return gson.fromJson(string, WeeklyDataModel.WeeklyData.DailyData::class.java)
    }

    @TypeConverter
    fun dailyItemToString(entity: WeeklyDataModel.WeeklyData.DailyItem): String {
        return gson.toJson(entity)
    }

    @TypeConverter
    fun stringToDailyItem(string: String): WeeklyDataModel.WeeklyData.DailyItem {
        return gson.fromJson(string, WeeklyDataModel.WeeklyData.DailyItem::class.java)
    }
}