package com.program.jetpack.sample.room

import android.content.Intent
import androidx.compose.runtime.*
import com.program.common.MainListScreen
import com.program.jetpack.sample.room.RoomData.ROOM_CODELABS_ROUTE
import com.program.jetpack.sample.room.RoomData.ROOM_SIMPLE_ROUTE
import com.program.jetpack.sample.room.codelabs.roomwordssample.RoomWordActivity
import com.program.jetpack.sample.room.roomsample.SampleRoomActivity

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
            ROOM_SIMPLE_ROUTE -> Intent(context, SampleRoomActivity::class.java)
            ROOM_CODELABS_ROUTE -> Intent(context, RoomWordActivity::class.java)
            else -> {
                null
            }
        }
    }
}


