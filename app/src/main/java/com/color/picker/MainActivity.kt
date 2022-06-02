package com.color.picker

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.color.picker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.colorPalette.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                    val colorPaletteBitmap = binding.colorPalette.getBitmap()
                    val pixels = colorPaletteBitmap?.getPixel(
                        motionEvent.x.toInt(),
                        motionEvent.y.toInt()
                    ) ?: 0

                    binding.selectedColor.setBackgroundColor(pixels.toRgb())

                    binding.selectedColor.animate()
                        .x(motionEvent.x)
                        .y(motionEvent.y)
                        .duration = 400
                }
            }
            false
        }
    }
}