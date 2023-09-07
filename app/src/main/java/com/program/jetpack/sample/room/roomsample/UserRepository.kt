package com.program.jetpack.sample.room.roomsample

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    fun getAllUser(): Flow<List<User>> = userDao.getAllUser()

    @WorkerThread
    suspend fun addUser(user: User) {
        userDao.addUsers(user)
    }

}