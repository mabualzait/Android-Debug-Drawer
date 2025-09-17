package com.debugdrawer.utils

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Centralized logging utility for the debug drawer.
 * Provides consistent logging across all modules.
 */
@Singleton
class Logger @Inject constructor() {

    private val tagPrefix = "DebugDrawer"

    fun v(tag: String, message: String) {
        Log.v("$tagPrefix:$tag", message)
    }

    fun d(tag: String, message: String) {
        Log.d("$tagPrefix:$tag", message)
    }

    fun i(tag: String, message: String) {
        Log.i("$tagPrefix:$tag", message)
    }

    fun w(tag: String, message: String) {
        Log.w("$tagPrefix:$tag", message)
    }

    fun e(tag: String, message: String, throwable: Throwable? = null) {
        Log.e("$tagPrefix:$tag", message, throwable)
    }

    fun wtf(tag: String, message: String, throwable: Throwable? = null) {
        Log.wtf("$tagPrefix:$tag", message, throwable)
    }
}
