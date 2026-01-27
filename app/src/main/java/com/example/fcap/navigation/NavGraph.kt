package com.example.fcap.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fcap.ui.history.HistoryScreen
import com.example.fcap.ui.session.SessionScreen
import com.example.fcap.viewmodel.SessionViewModel

@Composable
fun NavGraph(
    viewModel: SessionViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Session.route
    ) {
        composable(NavRoutes.Session.route) {
            SessionScreen(
                viewModel = viewModel,
                onNavigateToHistory = {
                    navController.navigate(NavRoutes.History.route)
                }
            )
        }

        composable(NavRoutes.History.route) {
            HistoryScreen(
                sessions = viewModel.history.collectAsState().value,
                onDelete = { session ->
                    viewModel.deleteSession(session)
                }
            )
        }

    }
}
