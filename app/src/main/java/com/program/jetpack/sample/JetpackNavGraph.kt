package com.program.jetpack.sample

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.program.jetpack.sample.guidearchitecture.GuideArchitectureScreen
import com.program.jetpack.sample.lifecycle.LifecycleScreen
import com.program.jetpack.sample.JetpackDestination.GUIDE_ARCHITECTURE_ROUTE
import com.program.jetpack.sample.JetpackDestination.LIFECYCLE_ROUTE
import com.program.jetpack.sample.JetpackDestination.MODULARIZATION_ROUTE
import com.program.jetpack.sample.JetpackDestination.NAVIGATION_ROUTE
import com.program.jetpack.sample.JetpackDestination.PAGING_ROUTE
import com.program.jetpack.sample.JetpackDestination.ROOM_ROUTE
import com.program.jetpack.sample.modularization.ModularizationScreen
import com.program.jetpack.sample.navigation.NavigationScreen
import com.program.jetpack.sample.paging.PagingScreen
import com.program.jetpack.sample.room.RoomScreen


object JetpackDestination {
    const val GUIDE_ARCHITECTURE_ROUTE = "GuideToAppArchitecture"
    const val MODULARIZATION_ROUTE = "Modularization"
    const val LIFECYCLE_ROUTE = "Lifecycle系列"
    const val ROOM_ROUTE = "Room"
    const val PAGING_ROUTE = "Paging"
    const val NAVIGATION_ROUTE = "Navigation"
}

val jetpackListData = listOf(
    GUIDE_ARCHITECTURE_ROUTE,
    MODULARIZATION_ROUTE,
    LIFECYCLE_ROUTE,
    ROOM_ROUTE,
    PAGING_ROUTE,
    NAVIGATION_ROUTE,
)

fun NavGraphBuilder.jetpackNavGraph() {
    composable(GUIDE_ARCHITECTURE_ROUTE) {
        GuideArchitectureScreen()
    }
    composable(MODULARIZATION_ROUTE) {
        ModularizationScreen()
    }
    composable(LIFECYCLE_ROUTE) {
        LifecycleScreen()
    }
    composable(ROOM_ROUTE) {
        RoomScreen()
    }
    composable(PAGING_ROUTE) {
        PagingScreen()
    }
    composable(NAVIGATION_ROUTE) {
        NavigationScreen()
    }
}