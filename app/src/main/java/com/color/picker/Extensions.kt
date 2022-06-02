package com.color.picker

import android.graphics.Color

fun Int.toRgb(): Int = Color.rgb(Color.red(this), Color.green(this), Color.blue(this))