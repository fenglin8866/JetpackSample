package com.program.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.program.main.ui.theme.JetpackSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {

    val appState = rememberAppState()

    Scaffold(bottomBar = {
        if (appState.isShowBottomBar) {
            BottomNavigation(backgroundColor = MaterialTheme.colorScheme.surface) {
                bottomScreens.forEach {
                    BottomNavigationItem(
                        icon = {
                            Icon(it.icon, contentDescription = null)
                        },
                        label = { Text(it.route) },
                        //不需要使用state的变量，因为“selected”内部已经封装了state
                        selected = appState.currentRoute == it.route,
                        onClick = {
                            appState.navigateToBottomBarRoute(it.route)
                        })
                    Log.d("xxh","it=$it currentRoute=${appState.currentRoute} ")
                }

            }
        }
    }) { innerPadding ->
        AppNavHost(
            navController = appState.navController,
            modifier = Modifier.padding(innerPadding),
        )
    }
}