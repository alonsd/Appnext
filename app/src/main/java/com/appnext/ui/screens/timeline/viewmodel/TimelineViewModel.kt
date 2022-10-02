package com.appnext.ui.screens.timeline.viewmodel

import androidx.lifecycle.ViewModel
import com.appnext.data.repository.AppnextRepository
import com.appnext.model.ui_models.TimelineListItem
import com.appnext.ui.screens.dashboard.viewmodel.DashboardViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TimelineViewModel(private val repository: AppnextRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardViewModel.UiState())
    val uiState = _uiState.asStateFlow()

    init {

    }


    data class UiState(
        val timelineData: List<TimelineListItem> = emptyList(),
        val errorMessage: String = "",
        val state: State = State.Initial
    ) {
        enum class State {
            Data,
            Error,
            Initial
        }
    }
}