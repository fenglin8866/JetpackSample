package com.program.jetpack.sample.room.roomsample

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Dao内方法是与数据库的交互，
 * 为了防止数据库操作阻塞主线程，所有操作必须是异步的
 */
@Dao
interface UserDao {
    /**
     * 自动实现异步
     */
    @Insert
    suspend fun addUsers(vararg user: User)

    /**
     * 需要手动实现异步
     */
    @Insert
    fun addUsers2(vararg user: User)

    @Delete
    fun delUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("select * from user_info where id in (:ids)")
    fun getUserForId(ids: List<Int>): List<User>

    @Query("select * from user_info where name=:name")
    fun getUserForId(name: String): LiveData<List<User>>

    @Query("select * from user_info")
    fun getAllUser(): Flow<List<User>>

    @Query("select * from user_info")
    fun getUsers(): LiveData<List<User>>

    /**
     * 1.需要手动实现异步，
     * 2.在数据库变化是需要主动查询
     */
    @Query("select * from user_info")
    fun getUsers3(): List<User>
}