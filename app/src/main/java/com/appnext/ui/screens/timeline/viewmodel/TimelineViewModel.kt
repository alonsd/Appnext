package com.appnext.ui.screens.timeline.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appnext.data.repository.AppnextRepository
import com.appnext.model.ui_models.TimelineListItem
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TimelineViewModel(private val repository: AppnextRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        getTimelineItems()
    }

    private fun getTimelineItems() = viewModelScope.launch {
        when(val response = repository.getTimelineItems()) {
            is NetworkResponse.Success -> {
                _uiState.update {
                    it.copy(timelineData = response.body, state = UiState.State.Data)
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