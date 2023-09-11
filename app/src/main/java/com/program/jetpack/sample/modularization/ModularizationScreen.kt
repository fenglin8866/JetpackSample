package com.program.jetpack.sample.modularization

import androidx.compose.runtime.Composable
import com.program.jetpack.sample.common.MainListScreen
import com.program.jetpack.sample.modularization.ModularizationData.COMPONENT
import com.program.jetpack.sample.modularization.ModularizationData.MODULARIZATION
import com.program.jetpack.sample.modularization.ModularizationData.PLUG_IN

object ModularizationData {
    const val MODULARIZATION = "模块化"
    const val COMPONENT = "组件化"
    const val PLUG_IN = "脱壳，插件化"

}

val listData = listOf(
    MODULARIZATION, COMPONENT, PLUG_IN
)

@Composable
fun ModularizationScreen() {
    MainListScreen(listData) { content, context ->
        when (content) {
            else -> {
                null
            }
        }
    }
}