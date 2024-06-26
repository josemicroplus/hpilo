package com.example.hpilo.iloapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.hpilo.iloapp.ui.MainScreen
import com.example.hpilo.iloapp.ui.ServerListScreen
import com.example.hpilo.ui.theme.HpiloTheme
import com.example.hpilo.iloapp.viewmodel.IloViewModel // Certifique-se de que esta linha está presente

class MainActivity : ComponentActivity() {
    private val viewModel: IloViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HpiloTheme {

                val navController = rememberNavController()
                Surface(color = MaterialTheme.colorScheme.background) {
                    MainScreen(viewModel, navController)
                }
            }
        }
    }
}
