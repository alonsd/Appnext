package com.appnext.ui.screens.dashboard.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.appnext.databinding.ViewholderWeeklyProgressBinding
import com.appnext.model.ui_models.WeeklyProgressListItem

class WeeklyProgressViewHolder(private val binding : ViewholderWeeklyProgressBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model : WeeklyProgressListItem){
        binding.weeklyProgressDayName.text = model.dayOfWeek
        if (model.isToday) binding.weeklyProgressCurrentDayIndicator.isVisible = true
    }

}