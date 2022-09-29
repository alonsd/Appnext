package com.appnext.ui.application_flow.dashboard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.appnext.databinding.FragmentDashboardBinding
import com.appnext.ui.application_flow.dashboard.viewmodel.DashboardViewModel
import com.appnext.utils.extensions.launchAndRepeatWithViewLifecycle
import org.koin.android.ext.android.get

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private val dashboardViewModel = get<DashboardViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeActions()
    }

    private fun observeActions() = launchAndRepeatWithViewLifecycle {

        dashboardViewModel.uiState.collect { uiState ->

            when (uiState.state) {
                DashboardViewModel.UiState.State.Data -> {
                    Toast.makeText(requireContext(), uiState.weeklyData.toString(), Toast.LENGTH_LONG).show()
                }
                DashboardViewModel.UiState.State.Error -> {
                    Toast.makeText(requireContext(), uiState.errorMessage, Toast.LENGTH_LONG).show()
                }
                DashboardViewModel.UiState.State.Initial -> Unit
            }
        }
    }

}