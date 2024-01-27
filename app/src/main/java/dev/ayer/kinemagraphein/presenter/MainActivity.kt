package dev.ayer.kinemagraphein.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dev.ayer.kinemagraphein.presenter.navigation.AppNavHost
import dev.ayer.kinemagraphein.presenter.theme.KinemaGrapheinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KinemaGrapheinApp()
        }
    }
}

@Composable
fun KinemaGrapheinApp() {
    KinemaGrapheinTheme {
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