package com.appnext.ui.screens.timeline.adapter

import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.appnext.R
import com.appnext.databinding.ViewholderTimelineItemBinding
import com.appnext.model.ui_models.TimelineListItem

class TimelineListItemViewHolder(private val binding: ViewholderTimelineItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: TimelineListItem) {
        val context = binding.root.context
        binding.timelineItemTextviewCurrentDayNumber.text = model.currentDayNumber
        binding.timelineItemTextviewCurrentDayName.text = model.currentDayName
        binding.timelineItemStepsValue.text = context.getString(
            R.string.viewholder_time_item_steps_value,
            model.dailyActivity,
            model.dailyGoal
        )
        binding.timelineItemCircleviewKcalIndicator.circleColor = ContextCompat.getColor(binding.root.context, model.itemColor)
        binding.timelineItemKcalValue.text = context.getString(R.string.viewholder_time_item_kcal_value, model.kcal)
        binding.timelineItemCircleviewDistanceIndicator.circleColor = ContextCompat.getColor(binding.root.context, model.itemColor)
        binding.timelineItemDistanceValue.text =
            if (model.distanceMeters < 1000) {
                context.getString(R.string.viewholder_time_item_distance_in_meters, model.distanceMeters.toString())
            } else {
                context.getString(R.string.viewholder_time_item_distance_in_kilometers, model.distanceMeters.toString())
            }
        binding.timelineItemCircularprogressview.strokeColor = ContextCompat.getColor(binding.root.context, model.itemColor)
        binding.timelineItemCurrentDayIndicator.isInvisible = model.isToday.not()
        var progress = (model.dailyGoal.toFloat() / model.dailyActivity.toFloat()) / 10
        if (progress > 1.00) progress = 1.00f // TODO: continue from progress
        binding.timelineItemCircularprogressview.progress = progress
    }
}