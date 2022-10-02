package com.appnext.ui.screens.timeline.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appnext.R
import com.appnext.databinding.FragmentTimelineBinding
import com.appnext.ui.screens.dashboard.viewmodel.DashboardViewModel
import com.appnext.ui.screens.timeline.adapter.TimelineListAdapter
import com.appnext.ui.screens.timeline.viewmodel.TimelineViewModel
import org.koin.android.ext.android.get

class TimelineFragment : Fragment() {

    //UI Related
    private lateinit var binding : FragmentTimelineBinding
    private lateinit var adapter : TimelineListAdapter

    //Dependency Injection
    private val dashboardViewModel = get<TimelineViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTimelineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}