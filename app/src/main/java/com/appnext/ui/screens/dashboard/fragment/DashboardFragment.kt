package com.appnext.ui.screens.dashboard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.appnext.databinding.FragmentDashboardBinding
import com.appnext.model.ui_models.WeeklyProgressListItem
import com.appnext.ui.screens.dashboard.adapter.WeeklyProgressAdapter
import com.appnext.ui.screens.dashboard.viewmodel.DashboardViewModel
import com.appnext.utils.extensions.launchAndRepeatWithViewLifecycle
import com.appnext.utils.extensions.setHorizontalAdapter
import org.koin.android.ext.android.get

class DashboardFragment : Fragment() {

    //UI Related
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var adapter : WeeklyProgressAdapter

    //Dependency Injection
    private val dashboardViewModel = get<DashboardViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUiState()
    }

    private fun observeUiState() = launchAndRepeatWithViewLifecycle {

        dashboardViewModel.uiState.collect { uiState ->

            when (uiState.state) {
                DashboardViewModel.UiState.State.Data -> {
                    initAdapter(uiState.weeklyData)
                }
                DashboardViewModel.UiState.State.Error -> {
                    Toast.makeText(requireContext(), uiState.errorMessage, Toast.LENGTH_LONG).show()
                }
                DashboardViewModel.UiState.State.Initial -> Unit
            }
        }
    }

    private fun initAdapter(weeklyProgressListItems: List<WeeklyProgressListItem>) {
        adapter = WeeklyProgressAdapter()
        binding.recyclerviewWeeklyProgress.setHorizontalAdapter(requireContext(), adapter)
        adapter.submitList(weeklyProgressListItems)
    }

}