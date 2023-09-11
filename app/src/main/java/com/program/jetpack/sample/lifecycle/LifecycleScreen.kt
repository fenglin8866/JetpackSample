package com.program.jetpack.sample.lifecycle

import androidx.compose.runtime.*
import com.program.common.MainListScreen
import com.program.jetpack.sample.lifecycle.LifecycleData.LIFECYCLE
import com.program.jetpack.sample.lifecycle.LifecycleData.LIVEDATA
import com.program.jetpack.sample.lifecycle.LifecycleData.SAVEDSTATE
import com.program.jetpack.sample.lifecycle.LifecycleData.VIEWMODEL

object LifecycleData {
    const val LIFECYCLE = "Lifecycle"
    const val VIEWMODEL = "ViewModel"
    const val LIVEDATA = "LiveData"
    const val SAVEDSTATE = "SavedState"

}

val listData = listOf(
    LIFECYCLE, VIEWMODEL, LIVEDATA, SAVEDSTATE
)

@Composable
fun LifecycleScreen() {
    MainListScreen(listData) { content, context ->
        when (content) {
            else -> {
                null
            }
        }
    }
}


