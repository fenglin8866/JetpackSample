package com.program.thinking

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.program.thinking.ThinkingDestination.FUNCTIONAL_ROUTE
import com.program.thinking.ThinkingDestination.IOC_ROUTE
import com.program.thinking.ThinkingDestination.REACTIVE_ROUTE
import com.program.thinking.ioc.IocScreen


object ThinkingDestination {
    const val IOC_ROUTE = "ioc"
    const val FUNCTIONAL_ROUTE = "functional"
    const val REACTIVE_ROUTE = "reactive"

}

val thinkingListData = listOf(
    IOC_ROUTE,
    FUNCTIONAL_ROUTE,
    REACTIVE_ROUTE,
)

fun NavGraphBuilder.thinkingNavGraph() {
    composable(IOC_ROUTE) {
        IocScreen()
    }
    composable(FUNCTIONAL_ROUTE) {

    }
    composable(REACTIVE_ROUTE) {

    }
}