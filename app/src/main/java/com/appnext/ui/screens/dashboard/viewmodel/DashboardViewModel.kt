package com.appnext.ui.screens.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appnext.data.repository.AppnextRepository
import com.appnext.model.ui_models.WeeklyProgressListItem
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DashboardViewModel(private val repository: AppnextRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    private val uiEvent = _uiEvent.asSharedFlow()

    private val _uiAction = MutableSharedFlow<UiAction>()
    val uiAction = _uiAction.asSharedFlow()


    init {
        getWeeklyData()
        observeUiEvent()
    }

    private fun observeUiEvent() = viewModelScope.launch {
        uiEvent.collect { event ->
            when (event) {
                UiEvent.TimelineClicked -> {
                    submitAction(UiAction.NavigateToTimelineScreen)
                }
            }
        }
    }


    private fun getWeeklyData() = viewModelScope.launch(Dispatchers.IO) {
        when (val response = repository.getWeeklyProgressItems()) {
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

    private fun submitAction(uiAction: UiAction) = viewModelScope.launch {
        _uiAction.emit(uiAction)
    }

    fun submitEvent(uiEvent: UiEvent) = viewModelScope.launch {
        _uiEvent.emit(uiEvent)
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

    sealed interface UiEvent {
        object TimelineClicked : UiEvent
    }

    sealed interface UiAction {
        object NavigateToTimelineScreen : UiAction
    }

}