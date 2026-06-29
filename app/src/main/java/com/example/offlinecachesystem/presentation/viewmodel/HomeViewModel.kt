package com.example.offlinecachesystem.presentation.viewmodel

import android.util.Log
import com.example.offlinecachesystem.core.base.BaseViewModel
import com.example.offlinecachesystem.core.network.NetworkResult
import com.example.offlinecachesystem.domain.usecase.GetProductsUseCase
import com.example.offlinecachesystem.domain.usecase.SyncProductsUseCase
import com.example.offlinecachesystem.presentation.screens.home.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val syncProductsUseCase: SyncProductsUseCase,
) : BaseViewModel() {

    private val _uiState =
        MutableStateFlow(HomeUiState())

    val uiState = _uiState.asStateFlow()

    init {
        observeProducts()
        syncProducts()
    }

    private fun observeProducts() {

        launch {

            getProductsUseCase()
                .collect { products ->

                    _uiState.value =
                        _uiState.value.copy(
                            products = products
                        )
                }
        }
    }

    private fun syncProducts() {

        launch {

            _uiState.value =
                _uiState.value.copy(
                    isLoading = true,
                    errorMessage = null
                )

            when(val result = syncProductsUseCase()) {

                is NetworkResult.Success -> {

                    _uiState.value =
                        _uiState.value.copy(
                            isLoading = false
                        )
                }

                is NetworkResult.Error -> {

                    _uiState.value =
                        _uiState.value.copy(
                            isLoading = false,
                            errorMessage = result.error.toString()
                        )
                }

                is NetworkResult.Loading -> Unit
            }
        }
    }
}