package com.hpilo.iloapp.ui

import androidx.compose.runtime.Composable
import com.example.hpilo.iloapp.viewmodel.IloViewModel

@Composable
fun IloApp(viewModel: IloViewModel) {
    MainScreen(viewModel)
}
