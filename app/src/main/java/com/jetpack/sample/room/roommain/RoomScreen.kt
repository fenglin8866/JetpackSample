package com.jetpack.sample.room.roommain

import android.content.Intent
import androidx.compose.runtime.*
import com.jetpack.sample.common.MainListScreen
import com.jetpack.sample.room.sample.RoomActivity
import com.jetpack.sample.room.codelabs.roomwordssample.RoomWordActivity
import com.jetpack.sample.room.roommain.RoomData.ROOM_CODELABS_ROUTE
import com.jetpack.sample.room.roommain.RoomData.ROOM_SIMPLE_ROUTE

object RoomData {
    const val ROOM_SIMPLE_ROUTE = "Room_Simple"
    const val ROOM_CODELABS_ROUTE = "Room_Codelabs"

}

val listData = listOf(
    ROOM_SIMPLE_ROUTE,
    ROOM_CODELABS_ROUTE,
)

@Composable
fun RoomScreen() {
    MainListScreen(listData) { content, context->
        when (content) {
            ROOM_SIMPLE_ROUTE -> Intent(context, RoomActivity::class.java)
            ROOM_CODELABS_ROUTE -> Intent(context, RoomWordActivity::class.java)
            else -> {
                null
            }
        }
    }
}


