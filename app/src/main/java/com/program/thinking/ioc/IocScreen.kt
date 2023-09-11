package com.program.thinking.ioc

import androidx.compose.runtime.Composable
import com.program.common.MainListScreen
import com.program.thinking.ioc.IocData.DAGGER
import com.program.thinking.ioc.IocData.HILT

object IocData {
    const val DAGGER = "Dagger"
    const val HILT = "Hilt"

}

val listData = listOf(
    DAGGER,
    HILT,
)

@Composable
fun IocScreen() {
    MainListScreen(listData) { content, context ->
        when (content) {
            // DAGGER -> Intent(context, RoomActivity::class.java)
            // HILT -> Intent(context, RoomWordActivity::class.java)
            else -> {
                null
            }
        }
    }
}