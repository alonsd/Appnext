package com.appnext.model.ui_models

import com.appnext.utils.adapter.DefaultAdapterDiffUtilCallback

data class WeeklyProgressListItem(
    val dayOfWeek: String,
    val dailyActivity: Int,
    val dailyGoal: Int,
    val isToday : Boolean,
    val highestValue : Int
) : DefaultAdapterDiffUtilCallback.ModelWithId {
    override fun fetchId(): String = dayOfWeek
}
