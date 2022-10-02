package com.appnext.model.ui_models

import androidx.annotation.ColorRes
import com.appnext.utils.adapter.DefaultAdapterDiffUtilCallback

data class TimelineListItem(
    val currentDayNumber: String,
    val currentDayName: String,
    @ColorRes val itemColor: Int,
    val distanceMeters: Int,
    val kcal: String,
    val isToday : Boolean,
    val dailyActivity : String,
    val dailyGoal : String
) : DefaultAdapterDiffUtilCallback.ModelWithId{
    override fun fetchId(): String = currentDayNumber

}
