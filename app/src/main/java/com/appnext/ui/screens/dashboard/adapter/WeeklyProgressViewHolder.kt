package com.appnext.ui.screens.dashboard.adapter

import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.appnext.databinding.ViewholderWeeklyProgressBinding
import com.appnext.model.ui_models.WeeklyProgressListItem
import com.appnext.utils.extensions.getDpUnitsAsInt

class WeeklyProgressViewHolder(private val binding : ViewholderWeeklyProgressBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model : WeeklyProgressListItem){
        binding.weeklyProgressDayName.text = model.dayOfWeek
        if (model.isToday) binding.weeklyProgressCurrentDayIndicator.isVisible = true
        val maxHeight = 150
        val dailyActivityPercentage = (model.dailyActivity.toFloat() / model.highestValue) * 100
        val dailyActivityDp = (dailyActivityPercentage * maxHeight) / 100
        val dailyGoalPercentage = (model.dailyGoal.toFloat() / model.highestValue) * 100
        val dailyGoalDp = (dailyGoalPercentage * maxHeight) / 100
        binding.weeklyProgressActivity.updateLayoutParams {
            height = getDpUnitsAsInt(if (dailyActivityDp > maxHeight) maxHeight.toFloat() else dailyActivityDp, binding.root.context)
        }
        binding.weeklyProgressGoal.updateLayoutParams {
            height = getDpUnitsAsInt(if (dailyGoalDp > maxHeight) maxHeight.toFloat() else dailyGoalDp, binding.root.context)
        }
    }

}