package com.program.language

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.program.jetpack.sample.JetpackDestination
import com.program.common.ListScreenString
import com.program.jetpack.sample.guidearchitecture.GuideArchitectureScreen
import com.program.jetpack.sample.lifecycle.LifecycleScreen
import com.program.jetpack.sample.modularization.ModularizationScreen
import com.program.jetpack.sample.navigation.NavigationScreen
import com.program.jetpack.sample.paging.PagingScreen
import com.program.jetpack.sample.room.RoomScreen
import com.program.language.LanguageData.JAVA
import com.program.language.LanguageData.KOTLIN
import com.program.language.kotlinstudy.DelegateTestScreen
import com.program.language.kotlinstudy.KotlinNavGraph

object LanguageData {
    const val JAVA = "Java"
    const val KOTLIN = "Kotlin"
}

val listData = listOf(
    KOTLIN, JAVA
)

@Composable
fun LanguageMainScreen(click: (String) -> Unit) {
    ListScreenString(listData,click)
}

fun NavGraphBuilder.languageNavGraph() {
    composable(KOTLIN) {
        KotlinNavGraph(rememberNavController())
    }
    composable(JAVA) {

    }

}