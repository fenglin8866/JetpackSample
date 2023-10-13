package com.program.common.utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

object Util {
    fun toastShow(context: Context, content: String) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
    }

    fun isNetAvailable(context: Context): Boolean {
        try {
            val manager: ConnectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = manager.activeNetworkInfo
            if (networkInfo != null && networkInfo.isConnected) {
                return true
            }
        }catch (e:Exception){

        }
        return false
    }
}