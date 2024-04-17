package com.midtronics.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midtronics.domain.UseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

open class BaseViewModel<T>(
    private val useCase: UseCase<T>
): ViewModel() {

    private val _uiStateFlow: MutableStateFlow<UiState<T>> = MutableStateFlow(UiState.Idle)
    val uiStateFlow: MutableStateFlow<UiState<T>> = _uiStateFlow

    private suspend fun onReturnDataLoading() {
        _uiStateFlow.emit(UiState.Loading)
    }

    private suspend fun onReturnDataFetchFailure(throwable: Throwable) {
        val error = UiState.Error(throwable)
        _uiStateFlow.emit(error)
    }

    private suspend fun onReturnDataFetchSuccessful(data: T) {
        _uiStateFlow.emit(UiState.Success(data))
    }

    fun getData(query: String? = null) {
        viewModelScope.launch {
            onReturnDataLoading()
            useCase.apply(query)
                .onSuccess { data ->
                    onReturnDataFetchSuccessful(data)
                }
                .onFailure { throwable ->
                    onReturnDataFetchFailure(throwable)
                }
        }
    }
}