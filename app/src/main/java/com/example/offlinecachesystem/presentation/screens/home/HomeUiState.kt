package com.example.offlinecachesystem.presentation.screens.home

import com.example.offlinecachesystem.domain.model.Product

data class HomeUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val errorMessage: String? = null
)