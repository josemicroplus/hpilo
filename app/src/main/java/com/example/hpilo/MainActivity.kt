package com.hpilo.iloapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.hpilo.iloapp.ui.MainScreen
import com.hpilo.iloapp.ui.theme.HpiloAppTheme
import com.hpilo.iloapp.viewmodel.IloViewModel // Certifique-se de que esta linha está presente

class MainActivity : ComponentActivity() {
    private val viewModel: IloViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HpiloAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MainScreen(viewModel)
                }
            }
        }
    }
}
