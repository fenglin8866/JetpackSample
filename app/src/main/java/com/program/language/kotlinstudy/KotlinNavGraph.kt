package com.program.language.kotlinstudy

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.program.common.ListScreenString
import com.program.language.kotlinstudy.KotlinDestination.DELEGATE_ROUTE
import com.program.language.kotlinstudy.KotlinDestination.HOME_ROUTE


object KotlinDestination {
    const val HOME_ROUTE = "kotlin_home"
    const val DELEGATE_ROUTE = "delegate"

}

val kotlinListData = listOf(DELEGATE_ROUTE)

@Composable
fun KotlinNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = HOME_ROUTE) {
        composable(HOME_ROUTE) {
            ListScreenString(data = kotlinListData) {
                navController.navigate(it)
            }
        }
        composable(DELEGATE_ROUTE) {
            DelegateTestScreen()
        }
    }
}