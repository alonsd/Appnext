package com.appnext.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.appnext.data.source.local.dao.AppNextDao
import com.appnext.database.converter.DatabaseConverter
import com.appnext.model.database_entities.WeeklyDataEntity
import com.appnext.utils.application.App
import com.appnext.utils.constants.Database.APP_NEXT_DATABASE


@Database(entities = [WeeklyDataEntity::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverter::class)
abstract class AppNextDatabase : RoomDatabase() {


    abstract fun getAppNextDao () : AppNextDao

    companion object {

        private var instance : AppNextDatabase? = null

        @Synchronized
        fun getInstance(): AppNextDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(App.applicationContext(), AppNextDatabase::class.java, APP_NEXT_DATABASE)
                    .fallbackToDestructiveMigration().build()
            }
            return instance!!
        }
    }

}