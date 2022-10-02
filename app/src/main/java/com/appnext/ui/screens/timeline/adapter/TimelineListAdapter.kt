package com.appnext.ui.screens.timeline.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.appnext.databinding.ViewholderTimelineItemBinding
import com.appnext.model.ui_models.TimelineListItem
import com.appnext.utils.adapter.DefaultAdapterDiffUtilCallback

class TimelineListAdapter : ListAdapter<TimelineListItem, TimelineListItemViewHolder>(DefaultAdapterDiffUtilCallback<TimelineListItem>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineListItemViewHolder {
       val binding = ViewholderTimelineItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimelineListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimelineListItemViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}