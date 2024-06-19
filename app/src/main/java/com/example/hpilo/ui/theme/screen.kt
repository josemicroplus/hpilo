package com.example.hpilo.iloapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object RegisterServer : Screen("registerServer", "Register Server", Icons.Default.Add)
    object ServerList : Screen("serverList", "Server List", Icons.Default.List)
    object SystemInfo : Screen("systemInfo", "System Info", Icons.Default.Info)
    object DiskStatus : Screen("diskStatus", "Disk Status", Icons.Default.Info)
    object ServerDetail : Screen("serverDetail", "Server Detail", Icons.Default.Info)
}
