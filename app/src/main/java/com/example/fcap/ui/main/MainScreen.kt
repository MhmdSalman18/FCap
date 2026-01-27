package com.example.fcap.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fcap.navigation.BottomNavItem
import com.example.fcap.ui.components.AppBottomBar
import com.example.fcap.ui.components.AppTopBar
import com.example.fcap.ui.history.HistoryScreen
import com.example.fcap.ui.session.SessionScreen
import com.example.fcap.viewmodel.SessionViewModel

@Composable
fun MainScreen(
    viewModel: SessionViewModel
) {
    val navController = rememberNavController()
    val history by viewModel.history.collectAsState()

    Scaffold(
        topBar = {
            AppTopBar(
                title = "FCap",
                onClearClick = {
                    if (navController.currentDestination?.route == BottomNavItem.History.route) {
                        viewModel.clearHistory()
                    }
                }
            )
        },
        bottomBar = {
            AppBottomBar(navController)
        }
    )


    { padding ->

        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(padding)
        ) {

            composable(BottomNavItem.Home.route) {
                SessionScreen(
                    viewModel = viewModel,
                    onNavigateToHistory = {
                        navController.navigate(BottomNavItem.History.route)
                    }
                )
            }

            composable(BottomNavItem.Session.route) {
                SessionScreen(
                    viewModel = viewModel,
                    onNavigateToHistory = {
                        navController.navigate(BottomNavItem.History.route)
                    }
                )
            }

            composable(BottomNavItem.History.route) {
                val history by viewModel.history.collectAsState()

                HistoryScreen(
                    sessions = history,
                    onDelete = { session ->
                        viewModel.deleteSession(session)
                    }
                )
            }

        }
    }
}
