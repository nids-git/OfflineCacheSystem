package com.example.offlinecachesystem.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.offlinecachesystem.presentation.screens.home.components.ProductItem

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onProductClick: (Int) -> Unit
) {
    when {
        uiState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        uiState.errorMessage != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(uiState.errorMessage)
            }
        }
        else -> {
            LazyColumn {
                items(
                    items = uiState.products,
                    key = { it.id }
                ) { product ->
                    ProductItem (
                        product = product,
                        onClick = {
                            onProductClick(product.id)
                        }
                    )
                }
            }
        }
    }
}