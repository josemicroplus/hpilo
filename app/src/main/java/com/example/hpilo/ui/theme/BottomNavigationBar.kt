package com.example.hpilo.iloapp.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.hpilo.SystemInfoScreen
import com.example.hpilo.iloapp.viewmodel.IloViewModel

@Composable
fun SetupNavGraph(viewModel: IloViewModel,navController: NavHostController) {
    NavHost(navController = navController, startDestination = "serverList") {
        composable("registerServer") {
            RegisterServerScreen(viewModel = viewModel)
        }
        composable("serverList") {
            ServerListScreen(viewModel = viewModel, navController =navController )
        }
        composable("systemInfo") {
            SystemInfoScreen(viewModel = viewModel)
        }
        composable("diskStatus") {
            DiskStatusScreen(viewModel = viewModel)
        }
        composable("serverDetail") {
            ServerDetailScreen(viewModel = viewModel, serverId = 1, navController =navController )
        }

        composable(
            route = "serverDetail/{serverId}",
            arguments = listOf(navArgument("serverId") { type = NavType.IntType })
        ) { backStackEntry ->
            val serverId = backStackEntry.arguments?.getInt("serverId")
             ServerDetailScreen(viewModel, serverId!!,navController)
        }
    }
}
@Composable
fun BottomNavigationBar(viewModel: IloViewModel, navController: NavHostController) {
    val items = listOf(
        Screen.RegisterServer,
        Screen.ServerList,
        Screen.SystemInfo,
        Screen.DiskStatus
    )
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.label) },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
