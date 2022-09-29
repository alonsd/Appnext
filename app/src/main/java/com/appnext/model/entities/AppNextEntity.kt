package com.appnext.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.appnext.utils.constants.Database.APP_NEXT_TABLE


@Entity(tableName = APP_NEXT_TABLE)
data class AppNextEntity(@PrimaryKey val name : String)