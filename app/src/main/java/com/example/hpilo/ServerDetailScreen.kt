package com.hpilo.iloapp.ui

import android.util.Base64
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.hpilo.iloapp.viewmodel.IloViewModel

@Composable
fun ServerDetailScreen(viewModel: IloViewModel, serverId: Int, navController: NavHostController) {
    val server = viewModel.getServerById(serverId).observeAsState()
    val systemInfo by viewModel.systemInfo.observeAsState()
    val diskStatus by viewModel.diskStatus.observeAsState()
    val error by viewModel.error.observeAsState()

    LaunchedEffect(server.value) {
        server.value?.let {
            val auth = "Basic " + Base64.encodeToString("${it.username}:${it.password}".toByteArray(), Base64.NO_WRAP)
            val baseUrl = "https://${it.ip}"
            viewModel.fetchSystemInfo(baseUrl, auth)
            viewModel.fetchDiskStatus(baseUrl, auth)
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        server.value?.let {
            Text("Server IP: ${it.ip}")
            Spacer(modifier = Modifier.height(8.dp))

            systemInfo?.let {
                Text("System Model: ${it.model}")
                Text("Serial Number: ${it.serialNumber}")
                Text("BIOS Version: ${it.biosVersion}")
            }

            Spacer(modifier = Modifier.height(8.dp))

            diskStatus?.let {
                it.forEach { disk ->
                    Text("Disk: ${disk.name} - Health: ${disk.health} - Capacity: ${disk.capacity}")
                }
            }

            error?.let {
                Text("Error: $it", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
