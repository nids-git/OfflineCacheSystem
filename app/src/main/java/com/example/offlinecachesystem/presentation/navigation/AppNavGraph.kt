package com.example.offlinecachesystem.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.offlinecachesystem.presentation.screens.HomeScreen

@Composable
fun AppNavGraph(
    navController : NavHostController
){
    NavHost(
        navController = navController,
        startDestination = AppScreens.Home.route
    ){
        composable(AppScreens.Home.route) {
            HomeScreen(navController)
        }

       /* composable(
            route = AppScreens.Detail.route
        ) {
            DetailScreen()
        }*/
    }
}