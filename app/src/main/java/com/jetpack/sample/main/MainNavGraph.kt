package com.jetpack.sample.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jetpack.sample.main.MainDestination.PAGING_ROUTE
import com.jetpack.sample.main.MainDestination.ROOM_ROUTE
import com.jetpack.sample.main.MainDestination.MAIN_HOME_ROUTE
import com.jetpack.sample.paging.pagingmain.PagingScreen
import com.jetpack.sample.room.roommain.RoomScreen


object MainDestination {
    const val MAIN_HOME_ROUTE = "main_home"
    const val PAGING_ROUTE = "Paging"
    const val ROOM_ROUTE = "Room"

}

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = MAIN_HOME_ROUTE) {
        composable(MAIN_HOME_ROUTE) {
            MainScreen{
                navController.navigateSingleTopTo(it)
            }
        }
        composable(PAGING_ROUTE) {
            PagingScreen()
        }
        composable(ROOM_ROUTE) {
            RoomScreen()
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) {
    navigate(route) {
        launchSingleTop = true
        restoreState = true
        //获取栈中第一个NavDestination
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
    }
}