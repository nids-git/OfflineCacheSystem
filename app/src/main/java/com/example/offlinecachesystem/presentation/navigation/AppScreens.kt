package com.example.offlinecachesystem.presentation.navigation

sealed class AppScreens(val route : String) {
    data object Home : AppScreens("home")

    /*data object Detail : AppScreens("detail/{id}") {

        fun createRoute(id: Int): String {
            return "detail/$id"
        }
    }*/
}