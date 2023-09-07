package com.program.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberAppState(navController: NavHostController = rememberNavController()) =
    remember(navController) {
        AppState(navController)
    }

/**
 * 一般状态容器：界面元素状态提升 + 封装界面逻辑
 */
@Stable
class AppState(val navController: NavHostController) {

    private val bottomRoute = bottomScreens.map { it.route }

    /**
     * 当前导航路径，没有封装为状态，不会触发重组
     */
     val currentRoute: String?
        get() = navController.currentDestination?.route

    private val currentRouteAsState
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route

    val isShowBottomBar
        @Composable get() = currentRouteAsState in bottomRoute

    fun navigateToBottomBarRoute(route: String) {
        if (route != currentRoute) {
            navController.navigateSingleTopTo(route)
        }
    }

}