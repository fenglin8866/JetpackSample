package com.program.jetpack.sample.room.roomsample

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun addUsers(vararg user: User)

    @Delete
    fun delUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("select * from user_info")
    fun getAllUser(): Flow<List<User>>

    @Query("select * from user_info where id in (:ids)")
    fun getUserForId(ids:List<Int>): List<User>
}