package com.example.hpilo.iloapp.ui

import android.util.Base64
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
    val smartStorageDisks by viewModel.smartDisksList.observeAsState()
    val diskList by viewModel.diskList.observeAsState()

    LaunchedEffect(server.value) {
        server.value?.let {
            val auth = "Basic " + Base64.encodeToString("${it.username}:${it.password}".toByteArray(), Base64.NO_WRAP)
            val baseUrl = "https://${it.ip}"

            viewModel.fetchSystemInfo(baseUrl, auth)
            viewModel.fetchDiskStatus(baseUrl, auth)
            viewModel.fetchSmartDisksList(baseUrl, auth)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
          //  viewModelStoreOwnerScreenSplash.viewModelStore.clear()

        }
    }
    Column(modifier = Modifier.padding(16.dp)) {
        server.value?.let {
            Text("Server IP: ${it.ip}")

            Spacer(modifier = Modifier.height(8.dp))

            systemInfo?.let {
                Text("System Model: ${it.SystemModel}")
                Text("Hostame: ${it.HostName}")
                Text("Serial Number: ${it.SerialNumber}")
                Text("BIOS Version: ${it.BiosVersion}")
            }?: Text(text = "Loading Info Server...")

            Spacer(modifier = Modifier.height(8.dp))

            diskStatus?.let {
                Text("Estado SmartStorage: ${diskStatus!!.status.health}")
            }?: Text(text = "Loading SmartStorage...")
            diskList?.let {
                it.forEachIndexed { index, element ->
                    Text("SN: ${element.serialNumber} - Health: ${element.status.health} - Capacity: ${element.capacityGB}")

                }
            }?: Text(text = "Loading DiskList...")
            smartStorageDisks?.let {

                val auth = "Basic " + Base64.encodeToString(
                    "${server.value?.username}:${server.value?.password}".toByteArray(),
                    Base64.NO_WRAP
                )
                val baseUrl = "https://${server.value?.ip}"
                viewModel.fetchSmartDisksListStatus(baseUrl, auth)

            }?: Text(text = "Loading SmartStorage DiskList...")

            error?.let {
                Text("Error: $it", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
