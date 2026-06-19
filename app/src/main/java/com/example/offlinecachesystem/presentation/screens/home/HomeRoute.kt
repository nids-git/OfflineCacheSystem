package com.example.offlinecachesystem.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.offlinecachesystem.presentation.viewmodel.HomeViewModel

@Composable
fun HomeRoute(
    navController: NavController,
    homeViewModel : HomeViewModel = hiltViewModel()
){

    val uiState by homeViewModel.uiState.collectAsState()

    HomeScreen(
        uiState = uiState,
        onProductClick = {

        }
    )
}