package com.program.language.kotlinstudy

import android.app.Application

class Test {
    fun a(application: Application) {

    }
}

class Factory(val application: Application?, unused: Int) {
    @Suppress("SingletonConstructor")
    constructor() : this(null, 0)

    @Suppress("SingletonConstructor")
    constructor(application: Application) : this(application, 0)

    companion object {

        private var sInstance: Factory? = null

        @JvmStatic
        fun getInstance(application: Application): Factory {
            if (sInstance == null) {
                sInstance = Factory(application)
            }
            return sInstance!!
        }
    }

}


class Factory2(val application: Application?) {
    @Suppress("SingletonConstructor")
    constructor() : this(null)

    companion object {

        private var sInstance: Factory2? = null

        @JvmStatic
        fun getInstance(application: Application): Factory2 {
            if (sInstance == null) {
                sInstance = Factory2(application)
            }
            return sInstance!!
        }
    }

}


class Factory3() {

    val mApplication: Application?
        get() = null

    @Suppress("SingletonConstructor")
    constructor(application: Application) : this() {
        //不能给mApplication赋值
        //mApplication = application
    }

    companion object {

        private var sInstance: Factory2? = null

        @JvmStatic
        fun getInstance(application: Application): Factory2 {
            if (sInstance == null) {
                sInstance = Factory2(application)
            }
            return sInstance!!
        }
    }

}


class Factory4() {

    var mApplication: Application? = null

    @Suppress("SingletonConstructor")
    constructor(application: Application) : this() {
        mApplication = application
    }

    companion object {

        private var sInstance: Factory2? = null

        @JvmStatic
        fun getInstance(application: Application): Factory2 {
            if (sInstance == null) {
                sInstance = Factory2(application)
            }
            return sInstance!!
        }
    }

}