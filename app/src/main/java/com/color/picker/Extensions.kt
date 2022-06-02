package com.color.picker

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.util.Log
import android.widget.ImageView

fun ImageView.getBitmap(): Bitmap? {
    val bitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    this.draw(canvas)
    return bitmap
}

fun Int.toRgb(): Int = Color.rgb(Color.red(this), Color.blue(this), Color.green(this))