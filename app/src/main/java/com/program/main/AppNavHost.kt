package com.program.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.HomeMax
import androidx.compose.material.icons.filled.HomeMini
import androidx.compose.material.icons.filled.HomeWork
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.program.example.ExampleMainScreen
import com.program.jetpack.sample.JetpackMainScreen
import com.program.jetpack.sample.jetpackNavGraph
import com.program.language.LanguageMainScreen
import com.program.language.languageNavGraph
import com.program.thinking.ThinkingMainScreen
import com.program.thinking.thinkingNavGraph

interface MainDestinations {
    val icon: ImageVector
    val route: String
}

object Jetpack : MainDestinations {
    override val icon = Icons.Filled.Home
    override val route = "jetpack"
}

object Thinking : MainDestinations {
    override val icon = Icons.Filled.HomeMax
    override val route = "thinking"
}

object Examples : MainDestinations {
    override val icon = Icons.Filled.HomeMini
    override val route = "examples"
}

object Language : MainDestinations {
    override val icon = Icons.Filled.HomeWork
    override val route = "language"
}

val bottomScreens = listOf(Jetpack, Thinking, Examples, Language)

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = "jetpack",
        modifier = modifier
    ) {
        composable(Jetpack.route) {
            JetpackMainScreen {
                navController.navigate(it)
            }
        }

        composable(Thinking.route) {
            ThinkingMainScreen {
                navController.navigate(it)
            }
        }

        composable(Examples.route) {
            ExampleMainScreen()
        }

        composable(Language.route) {
            LanguageMainScreen {
                navController.navigate(it)
            }
        }

        jetpackNavGraph()

        thinkingNavGraph()

        languageNavGraph()
    }
}

