package com.program.jetpack.sample.room.roomsample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info")
data class User(
    @PrimaryKey val id: Int,
    val name: String,
    @ColumnInfo(name = "adr") val address: String
)
