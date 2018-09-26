package com.lhxia.game.core.obj

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import com.lhxia.app.R

class SampleSpirit(var bitmap: Bitmap) : Spirit {

    var startTime  = 0L

    val initX = Math.random() * 1920
    val initY = Math.random() * 1080

    var x : Int = initX.toInt()
    var y : Int = initY.toInt()

    var direction = 1

    override fun render(time: Long, canvas: Canvas) {

        canvas.drawBitmap(bitmap, x.toFloat(), y.toFloat(), paint)
//        canvas.drawCircle(x.toFloat(), y.toFloat(), 30f, paint)
    }

    override fun update(time: Long) {
        if (startTime == 0L){
            startTime = System.currentTimeMillis()
        }
        if (x > initX && y > initY){
            direction = -1
        }else if (x < 0 && y < 0){
            direction = 1
        }
        x += direction
        y += direction

    }

    companion object {

        val paint = Paint().apply {
            color = Color.RED
        }
    }
}