package com.jetpack.sample.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jetpack.sample.ioc.IocScreen
import com.jetpack.sample.lifecycle.LifecycleScreen
import com.jetpack.sample.main.MainDestination.IOC_ROUTE
import com.jetpack.sample.main.MainDestination.LIFECYCLE_ROUTE
import com.jetpack.sample.main.MainDestination.PAGING_ROUTE
import com.jetpack.sample.main.MainDestination.ROOM_ROUTE
import com.jetpack.sample.main.MainDestination.MAIN_HOME_ROUTE
import com.jetpack.sample.main.MainDestination.NAVIGATION_ROUTE
import com.jetpack.sample.navigation.NavigationScreen
import com.jetpack.sample.paging.PagingScreen
import com.jetpack.sample.room.RoomScreen


object MainDestination {
    const val MAIN_HOME_ROUTE = "main_home"
    const val LIFECYCLE_ROUTE = "Lifecycle系列"
    const val PAGING_ROUTE = "Paging"
    const val ROOM_ROUTE = "Room"
    const val NAVIGATION_ROUTE = "Navigation"
    const val IOC_ROUTE = "Ioc"

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
        composable(LIFECYCLE_ROUTE) {
            LifecycleScreen()
        }
        composable(NAVIGATION_ROUTE) {
            NavigationScreen()
        }
        composable(IOC_ROUTE) {
            IocScreen()
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