package com.example.hpilo.iloapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextAlign
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
            RegisterServerScreen(viewModel = viewModel, navController =navController)
        }
        composable("serverList") {
            viewModel.getAllServers()
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
            viewModel.clearInfo()
             ServerDetailScreen(viewModel, serverId!!,navController)
        }
    }
}
@Composable
fun TopNavigationBar(viewModel: IloViewModel, navController: NavHostController) {
    val items = listOf(
        Screen.RegisterServer,
        Screen.ServerList
    )
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

            NavigationBarItem(
                icon = { Icon(Icons.Default.Info, contentDescription = "Test") },
                label = { Text("Text", textAlign = TextAlign.Center) },
                selected = currentRoute == "s",
                onClick = {
                    navController.navigate("s") {
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
@Composable
fun BottomNavigationBar(viewModel: IloViewModel, navController: NavHostController) {
    val items = listOf(
        Screen.RegisterServer,
        Screen.ServerList
    )
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.label) },
                label = { Text(screen.label, textAlign = TextAlign.Center) },
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
