package com.example.hpilo.iloapp.ui

import android.util.Base64
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hpilo.iloapp.viewmodel.IloViewModel

@Composable
fun DiskStatusScreen(viewModel: IloViewModel) {
    val diskStatus by viewModel.SmartStorageStatus.observeAsState()
    val error by viewModel.error.observeAsState()

    LaunchedEffect(Unit) {
        val auth = "Basic " + Base64.encodeToString("Administrator:Dim0billi".toByteArray(), Base64.NO_WRAP)
        viewModel.fetchSmartStorageStatus("https://10.10.1.66/",auth)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        diskStatus?.let {
            /*it.forEach { disk ->
                Text("Disk: ${disk.Name} - Health: ${disk.Health} - Capacity: ${disk.Capacity}")
            }*/

            Text("Estado SmartStorage: ${diskStatus!!.status.health}")
        }

        error?.let {
            Text("Error: $it")
        }
    }
}
