package com.jetpack.sample.ioc

import androidx.compose.runtime.Composable
import com.jetpack.sample.common.MainListScreen
import com.jetpack.sample.ioc.IocData.DAGGER
import com.jetpack.sample.ioc.IocData.HILT

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