package com.program.jetpack.sample.room.roomsample

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    fun getAllUser(): Flow<List<User>> = userDao.getAllUser()

    fun getAllUser2(): LiveData<List<User>> = userDao.getUsers()

    fun getAllUser3(): List<User> = userDao.getUsers3()

    @WorkerThread
    suspend fun addUser(user: User) {
        userDao.addUsers(user)
    }

     fun addUser2(user: User) {
        userDao.addUsers2(user)
    }

}