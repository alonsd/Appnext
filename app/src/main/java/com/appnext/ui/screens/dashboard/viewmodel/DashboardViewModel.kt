package com.appnext.ui.screens.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appnext.data.repository.AppnextRepository
import com.appnext.model.ui_models.WeeklyProgressListItem
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(private val repository: AppnextRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()


    init {
        getWeeklyData()
    }


    private fun getWeeklyData() = viewModelScope.launch(Dispatchers.IO) {
        when (val response = repository.getWeeklyProgressListItem()) {
            is NetworkResponse.Success -> {
                _uiState.update {
                    it.copy(weeklyData = response.body, state = UiState.State.Data)
                }
            }

            is NetworkResponse.Error -> {
                val message = response.error.message ?: run { return@launch }
                _uiState.update { it.copy(errorMessage = message, state = UiState.State.Error) }
            }
            else -> Unit
        }
    }

    data class UiState(
        val weeklyData: List<WeeklyProgressListItem> = emptyList(),
        val errorMessage: String = "",
        val state: State = State.Initial
    ) {
        enum class State {
            Data,
            Error,
            Initial
        }
    }

    sealed interface UiEvent{
        object TimelineClicked
    }

    sealed interface UiAction {
        object NavigateToTimelineScreen
    }

}