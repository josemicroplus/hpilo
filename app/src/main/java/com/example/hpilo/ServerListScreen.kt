package com.example.hpilo.iloapp.ui

import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.R
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.hpilo.iloapp.model.Server
import com.example.hpilo.iloapp.viewmodel.IloViewModel

@Composable
fun ServerListScreen(viewModel: IloViewModel, navController: NavHostController) {
    val servers by viewModel.allServers.observeAsState(emptyList())

    Column(modifier = Modifier.fillMaxWidth().padding(top=10.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = com.example.hpilo.iloapp.R.drawable.microplus),"s"
        )
    }
    LazyColumn(modifier = Modifier.padding(16.dp).padding(top=45.dp)) {
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

            Text("Nome: ${server.name}")
            Text("IP: ${server.ip}")
            // Placeholder for disk status; this should be fetched dynamically
            Spacer(modifier = Modifier.height(8.dp))
            Row(){

                Text("Server Status:", modifier = Modifier.fillMaxWidth(0.75f))
                var cl:Color =Color.White;
                when (server.status) {
                    "OK" -> cl=Color.Green
                    "Waiting" -> cl=Color.Black
                    "Erro" -> cl=Color.Red
                    else -> { // Note the block
                        cl=Color.Yellow
                    }
                }
                Text("${server.status}", color = cl, textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth())
            }

        }
    }
}
