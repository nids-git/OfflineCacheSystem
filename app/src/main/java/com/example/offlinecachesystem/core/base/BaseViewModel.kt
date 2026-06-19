package com.example.offlinecachesystem.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    protected fun launch(
        onError: ((Throwable) -> Unit)? = null,
        block: suspend () -> Unit
    ) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            onError?.invoke(throwable)
        }
        viewModelScope.launch(exceptionHandler) {
            block()
        }
    }
}