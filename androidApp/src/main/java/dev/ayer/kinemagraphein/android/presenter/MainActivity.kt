package dev.ayer.kinemagraphein.android.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dev.ayer.kinemagraphein.android.presenter.navigation.AppNavHost
import dev.ayer.kinemagraphein.android.presenter.theme.QuantumTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuantumTheme {
                KinemaGrapheinApp()
            }
        }
    }
}

@Composable
fun KinemaGrapheinApp() {
    QuantumTheme {
        val navController = rememberNavController()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AppNavHost(
                navController = navController,
            )
        }
    }
}
