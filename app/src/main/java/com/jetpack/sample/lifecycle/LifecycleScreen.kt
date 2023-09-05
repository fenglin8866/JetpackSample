package com.jetpack.sample.lifecycle

import androidx.compose.runtime.*
import com.jetpack.sample.common.MainListScreen
import com.jetpack.sample.lifecycle.LifecycleData.LIFECYCLE
import com.jetpack.sample.lifecycle.LifecycleData.LIVEDATA
import com.jetpack.sample.lifecycle.LifecycleData.SAVEDSTATE
import com.jetpack.sample.lifecycle.LifecycleData.VIEWMODEL

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
          /*  LIFECYCLE -> Intent(context, RoomActivity::class.java)
            VIEWMODEL -> Intent(context, RoomActivity::class.java)
            LIVEDATA -> Intent(context, RoomActivity::class.java)
            SAVEDSTATE -> Intent(context, RoomActivity::class.java)*/
            else -> {
                null
            }
        }
    }
}


