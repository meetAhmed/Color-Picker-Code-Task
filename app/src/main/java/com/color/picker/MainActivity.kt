package com.color.picker

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.color.picker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var colorPaletteBitmap: Bitmap? = null
        var screenHeight = 0
        var screenWidth = 0
        var colorPaletteTopMargin = 0

        binding.parentView.post {
            colorPaletteBitmap = binding.colorPalette.drawable.toBitmap(config = Bitmap.Config.RGB_565)
            screenHeight = binding.parentView.height
            screenWidth = binding.parentView.width
            colorPaletteTopMargin = (screenHeight - binding.colorPalette.height) / 2
        }

        binding.colorPalette.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                    val pixels = colorPaletteBitmap?.getPixel(
                        motionEvent.x.toInt(),
                        motionEvent.y.toInt()
                    ) ?: 0

                    binding.selectedColor.setBackgroundColor(pixels.toRgb())

                    binding.selectedColor.animate()
                        .x(motionEvent.x)
                        .y(colorPaletteTopMargin + motionEvent.y)
                        .duration = 150

                    Logger.info(
                        "screenHeight: $screenHeight -- screenWidth: $screenWidth -- motionEvent.x: ${motionEvent.x} -- " +
                                "motionEvent.y: ${motionEvent.y} -- colorPaletteTopMargin: $colorPaletteTopMargin : pixels: $pixels -- pixels.toRgb(): ${pixels.toRgb()}"
                    )
                }
            }
            false
        }
    }
}