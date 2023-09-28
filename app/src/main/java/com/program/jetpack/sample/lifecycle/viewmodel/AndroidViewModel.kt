package com.program.jetpack.sample.lifecycle.viewmodel

import android.app.Application


open class AndroidViewModel(private val application: Application) : ViewModel() {
    @Suppress("UNCHECKED_CAST")
    fun <T : Application> getApplication(): T {
        return application as T
    }
}