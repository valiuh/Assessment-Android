package com.midtronics.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.midtronics.R
import com.midtronics.databinding.FragmentProfileBinding

abstract class BaseFragment<T>: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lifecycleScope.launchWhenStarted {
            getViewModel().uiStateFlow.collect { uiState ->
                Log.i("BaseFragment", uiState.toString())
                when (uiState) {
                    is UiState.Error -> {onError(uiState.throwable)}
                    is UiState.Loading -> {onLoading()}
                    is UiState.Success<T> -> {onSuccess(uiState.data)}
                    UiState.Idle -> {}
                }
            }
        }

        return getRootView()
    }

    private fun onError(throwable: Throwable? = null) {
        getLoaderView().visibility = View.GONE
        Toast.makeText(
            context,
            R.string.error,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onLoading() {
        getLoaderView().visibility = View.VISIBLE
    }

    abstract fun getViewModel(): BaseViewModel<T>

    abstract fun getLoaderView(): View

    abstract fun getRootView(): View

    abstract fun onSuccess(data: T)
}