package com.HPILO.iloapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.HPILO.iloapp.viewmodel.IloViewModel

@Composable
fun SystemInfoScreen(viewModel: IloViewModel) {
    val systemInfo by viewModel.systemInfo.observeAsState()
    val error by viewModel.error.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchSystemInfo(viewModel.auth)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        systemInfo?.let {
            Text("System Model: ${it.model}")
            Text("Serial Number: ${it.serialNumber}")
            Text("BIOS Version: ${it.biosVersion}")
        }

        error?.let {
            Text("Error: $it", color = MaterialTheme.colors.error)
        }
    }
}
