package com.example.hpilo.iloapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.hpilo.iloapp.model.Server
import com.example.hpilo.iloapp.viewmodel.IloViewModel

@Composable
fun ServerListScreen(viewModel: IloViewModel, navController: NavHostController) {
    val servers by viewModel.allServers.observeAsState(emptyList())

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(servers) { server ->
            ServerItem(server) {
                navController.navigate("serverDetail/${server.id}")
            }
        }
    }
}

@Composable
fun ServerItem(server: Server, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("IP: ${server.ip}")
            // Placeholder for disk status; this should be fetched dynamically
            Text("Disk Status: OK")
        }
    }
}
