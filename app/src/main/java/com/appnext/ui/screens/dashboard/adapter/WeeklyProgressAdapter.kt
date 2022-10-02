package com.appnext.ui.screens.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.appnext.databinding.ViewholderWeeklyProgressBinding
import com.appnext.model.ui_models.WeeklyProgressListItem
import com.appnext.utils.adapter.DefaultAdapterDiffUtilCallback

class WeeklyProgressAdapter : ListAdapter<WeeklyProgressListItem, WeeklyProgressViewHolder>(DefaultAdapterDiffUtilCallback<WeeklyProgressListItem>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyProgressViewHolder {
        val binding = ViewholderWeeklyProgressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeeklyProgressViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeeklyProgressViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}