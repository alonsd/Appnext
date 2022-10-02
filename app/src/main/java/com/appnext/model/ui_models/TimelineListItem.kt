package com.appnext.model.ui_models

import androidx.annotation.ColorRes
import com.appnext.utils.adapter.DefaultAdapterDiffUtilCallback

data class TimelineListItem(
    val currentDayNumber: String,
    val currentDayName: String,
    @ColorRes val circularProgressBarColor: Int,
    val distanceMeters: String,
    val kcal: String
) : DefaultAdapterDiffUtilCallback.ModelWithId{
    override fun fetchId(): String = currentDayNumber

}
