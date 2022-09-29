package com.appnext.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.appnext.model.entities.AppNextEntity


@Dao
interface AppNextDao {

    @Query("select * from AppNextTable")
    suspend fun getAppNextModel() : AppNextEntity
}