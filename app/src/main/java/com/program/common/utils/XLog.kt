package com.program.common.utils

import android.util.Log
import timber.log.Timber

object XLog {
    private const val TAG = "JetpackSample"
    private var debug = true

    fun debug(enable: Boolean) {
        debug = enable
    }

    fun d(tag: String, msg: String?) {
        if (debug) {
            Log.d(TAG, getMsg(tag, msg))
        }
    }

    fun d(msg: String) {
        if (debug) {
            Timber.tag(TAG).d(msg)
        }
    }

    fun i(tag: String, msg: String?) {
        Log.i(TAG, getMsg(tag, msg))
    }

    fun iDebug(tag: String, msg: String?) {
        if (debug) {
            Log.i(TAG, getMsg(tag, msg))
        }
    }

    fun w(tag: String, msg: String?) {
        Log.w(TAG, getMsg(tag, msg))
    }

    fun e(tag: String, msg: String?) {
        Log.e(TAG, getMsg(tag, msg))
    }

    private fun getMsg(tag: String, msg: String?): String {
        return "[$tag] $msg"
    }
}