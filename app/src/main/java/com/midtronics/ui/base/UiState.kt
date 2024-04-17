package com.midtronics.ui.base

sealed class UiState<out T> {
    data class Success<T>(val data: T) : UiState<T>()

    data class Error(val throwable: Throwable? = null) : UiState<Nothing>()

    object Loading : UiState<Nothing>()

    object Idle : UiState<Nothing>()
}