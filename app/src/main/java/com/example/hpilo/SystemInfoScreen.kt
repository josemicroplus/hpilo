package com.example.hpilo

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hpilo.iloapp.viewmodel.IloViewModel

@Composable
fun SystemInfoScreen(viewModel: IloViewModel) {
    val systemInfo by viewModel.systemInfo.observeAsState()
    val error by viewModel.error.observeAsState()

    LaunchedEffect(Unit) {
        //viewModel.fetchSystemInfo(viewModel.auth)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        systemInfo?.let {
            Text("System Model: ${it.SystemModel}")
            Text("Serial Number: ${it.SerialNumber}")
            Text("BIOS Version: ${it.BiosVersion}")
        }

        error?.let {
            Text("Error: $it")
        }
    }
}
