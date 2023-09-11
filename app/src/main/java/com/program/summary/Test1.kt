package com.program.summary

class Test1 {
}

data class User(val name: String)

class UserInfo {
    private var user: User? = null
    fun getUserInfo(): User? {
        val u = user
        return if (user == null) {
            User("xxh").also {
                user = it
            }
        } else {
            u
        }
    }

    //获取缓存数据
    fun getUserInfo2(): User? {
        return if (user == null) {
            User("xxh").also {
                user = it
            }
        } else {
            user
        }
    }
}
