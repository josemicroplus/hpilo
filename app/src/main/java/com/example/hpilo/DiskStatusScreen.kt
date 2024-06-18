package com.example.hpilo.iloapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hpilo.iloapp.viewmodel.IloViewModel

@Composable
fun DiskStatusScreen(viewModel: IloViewModel) {
    val diskStatus by viewModel.diskStatus.observeAsState()
    val error by viewModel.error.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchDiskStatus(viewModel.auth)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        diskStatus?.let {
            it.forEach { disk ->
                Text("Disk: ${disk.name} - Health: ${disk.health} - Capacity: ${disk.capacity}")
            }
        }

        error?.let {
            Text("Error: $it", color = MaterialTheme.colors.error)
        }
    }
}
