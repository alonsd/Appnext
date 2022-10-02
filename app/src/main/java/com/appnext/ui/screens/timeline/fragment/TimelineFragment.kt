package com.appnext.ui.screens.timeline.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.appnext.databinding.FragmentTimelineBinding
import com.appnext.model.ui_models.TimelineListItem
import com.appnext.ui.screens.timeline.adapter.TimelineListAdapter
import com.appnext.ui.screens.timeline.viewmodel.TimelineViewModel
import com.appnext.utils.extensions.launchAndRepeatWithViewLifecycle
import com.appnext.utils.extensions.setVerticalAdapter
import org.koin.android.ext.android.get

class TimelineFragment : Fragment() {

    //UI Related
    private lateinit var binding : FragmentTimelineBinding
    private lateinit var adapter : TimelineListAdapter

    //Dependency Injection
    private val timelineViewModel = get<TimelineViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTimelineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        observeUiState()
    }

    private fun observeUiState() = launchAndRepeatWithViewLifecycle {
        timelineViewModel.uiState.collect { uiState ->
            when(uiState.state) {
                TimelineViewModel.UiState.State.Data -> {
                    initTimelineAdapter(uiState.timelineData)
                }
                TimelineViewModel.UiState.State.Error -> {
                    Toast.makeText(requireContext(), uiState.errorMessage, Toast.LENGTH_SHORT).show()
                }
                TimelineViewModel.UiState.State.Initial -> Unit
            }
        }
    }

    private fun initTimelineAdapter(timelineData: List<TimelineListItem>) {
        adapter = TimelineListAdapter()
        binding.fragmentTimelineRecyclerview.setVerticalAdapter(requireContext(), adapter)
        adapter.submitList(timelineData)
    }
}