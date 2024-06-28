package com.example.hpilo.iloapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.hpilo.iloapp.viewmodel.IloViewModel

@Composable
fun IloApp(viewModel: IloViewModel, navController: NavHostController) {
    MainScreen(viewModel,navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: IloViewModel, navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(viewModel,navController) },
        //topBar = { TopNavigationBar(viewModel,navController) }
    ) { innerPadding ->
        // Your main content
        Surface(modifier = Modifier.padding(innerPadding)) {
            // Add your screen content here
            SetupNavGraph(viewModel,navController)
            //ServerListScreen(viewModel, navController)
        }
    }
}