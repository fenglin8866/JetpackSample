package com.program.language

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.program.common.ListScreenString
import com.program.language.LanguageData.JAVA
import com.program.language.LanguageData.KOTLIN
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