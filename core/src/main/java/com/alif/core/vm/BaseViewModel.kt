package com.alif.core.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    protected fun launchUI(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            block()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}