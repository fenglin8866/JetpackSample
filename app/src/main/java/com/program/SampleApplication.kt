/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.program

import android.app.Application
import com.program.example.persistence.AppExecutors
import com.program.example.persistence.DataRepository
import com.program.example.persistence.db.AppDatabase
import com.program.jetpack.sample.room.codelabs.roomwordssample.WordRepository
import com.program.jetpack.sample.room.codelabs.roomwordssample.WordRoomDatabase
import com.program.jetpack.sample.room.roomsample.UserDatabase
import com.program.jetpack.sample.room.roomsample.UserRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber

/**
 * Android Application class. Used for accessing singletons
 *
 * Application that sets up Timber in the DEBUG BuildConfig.
 * Read Timber's documentation for production setups.
 */
@HiltAndroidApp
class SampleApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { WordRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { WordRepository(database.wordDao()) }
    private val userDatabase by lazy { UserDatabase.getDatabase(this, applicationScope) }
    private val userDatabase2 by lazy { UserDatabase.getDatabase2(this) }
    val useRepository by lazy { UserRepository(userDatabase2.userDao()) }

    private lateinit var mAppExecutors: AppExecutors
    override fun onCreate() {
        super.onCreate()
        mAppExecutors = AppExecutors()
        // if (BuildConfig.DEBUG) {
        Timber.plant(Timber.DebugTree())
        //}
    }

    private fun getDatabase(): AppDatabase? {
        return AppDatabase.getInstance(this, mAppExecutors)
    }

    fun getProductRepository(): DataRepository? {
        return DataRepository.getInstance(getDatabase())
    }
}
