package com.example.fcap.navigation

sealed class NavRoutes(val route: String){
    object Session : NavRoutes("session")
    object History : NavRoutes("history")
}