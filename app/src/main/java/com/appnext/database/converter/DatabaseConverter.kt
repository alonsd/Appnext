package com.appnext.database.converter

import androidx.room.TypeConverter
import com.appnext.model.entities.AppNextEntity

class DatabaseConverter {

    @TypeConverter
    fun appNextModelToString(entity: AppNextEntity): String {
        return entity.name
    }

    @TypeConverter
    fun stringToAppNextModel(string: String): AppNextEntity {
        return AppNextEntity(string)
    }
}