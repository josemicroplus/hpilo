package com.example.hpilo.iloapp.ui

import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.hpilo.iloapp.model.Server
import com.example.hpilo.iloapp.viewmodel.IloViewModel

@Composable
fun ServerDetailScreen(viewModel: IloViewModel, serverId: Int, navController: NavHostController) {
    val server = viewModel.getServerById(serverId).observeAsState()
    val systemInfo by viewModel.systemInfo.observeAsState()
    val smartStorageStatus by viewModel.SmartStorageStatus.observeAsState()
    val error by viewModel.error.observeAsState()
    val ControllersStatus by viewModel.arrayControllersStatus.observeAsState()
    //val smartStorageDisks by viewModel.smartDisksList.observeAsState()
    val diskList by viewModel.diskList.observeAsState()
    val thermalInfo by viewModel.systemThermalInfo.observeAsState()
    val powerSupplies by viewModel.systemPowerSupplies.observeAsState()

    val scrollState = rememberScrollState()
    LaunchedEffect(server.value) {

        server.value?.let {
            val auth = "Basic " + Base64.encodeToString("${it.username}:${it.password}".toByteArray(), Base64.NO_WRAP)
            val baseUrl = "https://${it.ip}"

            viewModel.fetchSystemInfo(baseUrl, auth)
            viewModel.fetchSmartStorageStatus(baseUrl, auth)
            viewModel.getAllArrayControllers(baseUrl, auth)
            viewModel.getServerThermalInfo(baseUrl, auth)
            viewModel.getPowerSupplies(baseUrl, auth)

           // viewModel.fetchSmartDisksList(baseUrl, auth)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
          //  viewModelStoreOwnerScreenSplash.viewModelStore.clear()

        }
    }
    Column()
    {
        server.value?.let{
            Spacer(modifier = Modifier.height(8.dp))

            Row() {
               /* Button(onClick = {
                    viewModel.delete(it)
                    navController.popBackStack()
                },content = {
                    // Specify the icon using the icon parameter
                    Image(painter =painterResource(id = com.example.hpilo.iloapp.R.drawable.ic_launcher_foreground) , contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                })*/
                Text("Info ${it.name}" , fontSize = 25.sp, textAlign = TextAlign.Center,modifier = Modifier.
                fillMaxWidth())
            }
        }
    }
    Column(modifier = Modifier.padding(16.dp)
        .verticalScroll(state = scrollState)) {
        server.value?.let {

            Spacer(modifier = Modifier.height(30.dp))
            Text("Server IP: ${it.ip}")
            systemInfo?.let {
                Text("System Model: ${it.Model}")
                Text("Hostame: ${it.HostName}")
                Text("Serial Number: ${it.SerialNumber}")
                Text("BIOS Version: ${it.BiosVersion}")
            }?: Text(text = "Loading Info Server...")

            Spacer(modifier = Modifier.height(8.dp))

            Row() {
                Text("Controladora" , fontSize = 25.sp, textAlign = TextAlign.Center,modifier = Modifier.
                fillMaxWidth())
            }
            Spacer(modifier = Modifier.height(10.dp))
            smartStorageStatus?.let {

                Row() {

                    Text("Estado:",modifier = Modifier.
                    fillMaxWidth(0.75f))

                    var cl:Color;
                    if(it.status.health=="OK"){
                        cl=Color.Green
                    }else{
                        cl=Color.Red;
                    }
                    if(smartStorageStatus!!.status.health==""){
                        Text("Loading...",modifier = Modifier.
                        fillMaxWidth(), textAlign = TextAlign.Right,color=Color.Black)
                    }else {
                        Text("${smartStorageStatus!!.status.health}",modifier = Modifier.
                        fillMaxWidth(), textAlign = TextAlign.Right,color=cl)
                    }

                }
            }?: Text(text = "Loading SmartStorage...")
            Spacer(modifier = Modifier.height(10.dp))
            /*ControllersStatus?.let {
                it.forEachIndexed { index, element ->
                    Text("SN: ${element.name}")
                }
            }?: Text(text = "Loading DiskList...")*/
            diskList?.let {
                Row() {
                    Text("Discos" , fontSize = 25.sp, textAlign = TextAlign.Center,modifier = Modifier.
                    fillMaxWidth())
                }
                Row() {
                    Text("Serial Number" ,modifier = Modifier.
                    fillMaxWidth(0.75f))
                    Text("Health" ,modifier = Modifier.
                    fillMaxWidth())
                }
                if (it.size==0){
                    Row() {
                        Text(
                            "Loading...",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            fontSize = 15.sp
                        )
                    }
                }
                it.forEachIndexed { index, element ->
                    Row() {

                        Text("SN: ${element.serialNumber}" ,modifier = Modifier.
                        fillMaxWidth(0.75f))
                        if (element.status.health=="OK"){
                            Text("${element.status.health}",textAlign = TextAlign.Right,color= Color.Green,modifier = Modifier.
                            fillMaxWidth())
                        }else{

                            Text("${element.status.health}", textAlign = TextAlign.Right,color=Color.Red,modifier = Modifier.
                            fillMaxWidth())
                        }
                    }
                    Row() {
                        Text("Capacity: ${element.capacityGB}")
                    }
                    Spacer(modifier = Modifier.height(10.dp))

//                    Text("Estado: ${element.status.health}", color = MaterialTheme.colorScheme.error)
                }


                Spacer(modifier = Modifier.height(10.dp))

                Row() {
                    Text("Fans" , fontSize = 25.sp, textAlign = TextAlign.Center,modifier = Modifier.
                    fillMaxWidth())
                }
                Row() {
                    Text("Name" ,modifier = Modifier.
                    fillMaxWidth(0.75f))
                    Text("Health" ,modifier = Modifier.
                    fillMaxWidth())
                }
                Spacer(modifier = Modifier.height(10.dp))
                thermalInfo?.let{

                    if (it.fans.size==0){
                        Row() {
                            Text(
                                "Loading...",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                color = Color.Black,
                                fontSize = 15.sp
                            )
                        }
                    }
                    it.fans.forEachIndexed { index, element ->
                        Row() {
                            Text("${element.fanName}" ,modifier = Modifier.
                            fillMaxWidth(0.75f))
                            var cl:Color;
                            if(element.status.health=="OK"){
                                cl=Color.Green
                            }else{
                                cl=Color.Red;
                            }
                            Text("${element.status.health}" , textAlign = TextAlign.Right,modifier = Modifier.
                            fillMaxWidth(),color=cl)
                        }
                    }
                }

                Row() {
                    Text("Power Supplies" , fontSize = 25.sp, textAlign = TextAlign.Center,modifier = Modifier.
                    fillMaxWidth())
                }
                Row() {
                    Text("Name" ,modifier = Modifier.
                    fillMaxWidth(0.75f))
                    Text("Health" ,modifier = Modifier.
                    fillMaxWidth())
                }
                Spacer(modifier = Modifier.height(10.dp))
                powerSupplies?.let{

                    if (it.powerSupplies.size==0){
                        Row() {
                            Text(
                                "Loading...",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                color = Color.Black,
                                fontSize = 15.sp
                            )
                        }
                    }
                    it.powerSupplies.forEachIndexed { index, element ->
                        Row() {
                            Text("${element.name}" ,modifier = Modifier.
                            fillMaxWidth(0.75f))
                            var cl:Color;
                            if(element.status.health=="OK"){
                                cl=Color.Green
                            }else{
                                cl=Color.Red;
                            }
                            Text("${element.status.health}" , textAlign = TextAlign.Right,modifier = Modifier.
                            fillMaxWidth(),color=cl)
                        }
                    }
                }


            }

            error?.let {
                if(it!="") {
                    Text("Error: $it", color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}
