package com.color.picker

import android.util.Log
import androidx.viewbinding.BuildConfig

object Logger {

    private const val tag: String = "logsOfApp"

    fun info(text: String?) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, text ?: "text is empty")
        }
    }
}