package com.example.offlinecachesystem.presentation.viewmodel

import android.util.Log
import com.example.offlinecachesystem.core.base.BaseViewModel
import com.example.offlinecachesystem.core.network.NetworkResult
import com.example.offlinecachesystem.domain.usecase.GetProductsUseCase
import com.example.offlinecachesystem.presentation.screens.home.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getProducts()
    }

    private fun getProducts() {

        launch(
            onError = { throwable ->
                Log.e("HomeViewModel", "getProducts: Launch Error", throwable)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = throwable.message
                )
            }
        ) {
            getProductsUseCase().collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        Log.d("HomeViewModel", "getProducts: Loading")
                        _uiState.value = _uiState.value.copy(
                            isLoading = true,
                            errorMessage = null
                        )
                    }

                    is NetworkResult.Error -> {
                        Log.e("HomeViewModel", "getProducts: Error - ${result.error}")
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = result.error.toString()
                        )
                    }

                    is NetworkResult.Success -> {
                        Log.d("HomeViewModel", "getProducts: Success - ${result.data.size} items")

                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            products = result.data
                        )
                    }
                }
            }
        }
    }
}