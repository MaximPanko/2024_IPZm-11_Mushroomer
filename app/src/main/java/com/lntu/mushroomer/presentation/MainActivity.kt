package com.lntu.mushroomer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.lntu.domain.navigation.Navigator
import com.lntu.mushroomer.presentation.components.AppNavHost
import com.lntu.mushroomer.ui.theme.MushroomerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MushroomerTheme {
                val navHostController = rememberNavController()
                LaunchedEffect(navHostController) {
                    navigator.attach(navHostController)
                }
                AppNavHost(navHostController = navHostController)
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        navigator.detach()
    }

}