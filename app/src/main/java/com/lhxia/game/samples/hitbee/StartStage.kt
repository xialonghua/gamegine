package com.lhxia.game.samples.hitbee

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.lhxia.game.core.obj.Director
import com.lhxia.game.core.obj.Stage

class StartStage : Stage() {

    //标志位用于实现alpha增大减小
    var direction = 1
    //请稍后的点的个数。
    var dotCount = 0
    //当前字体透明度
    var alpha = 0f

    //透明度变化的时间
    var lastUpdateTime = 0L
    //请稍后后面的点点变化的时间
    var lastUpdateTime2 = 0L

    override fun update(time: Long) {
        super.update(time)

        if (lastUpdateTime == 0L){
            lastUpdateTime = System.currentTimeMillis()

            alpha = 0.2f
        }else {
            if (System.currentTimeMillis() - lastUpdateTime > 100){
                alpha += 0.1f * direction
                lastUpdateTime = System.currentTimeMillis()
                //每过100毫秒就改变一次透明度

            }
            if (System.currentTimeMillis() - lastUpdateTime2 > 300){
                //每过300毫秒就改变一次点的个数
                dotCount++
                //判断阈值，不能超出范围
                if (dotCount > 4){
                    dotCount = 0
                }
                lastUpdateTime2 = System.currentTimeMillis()
            }
            //判断阈值，设置渐变方向，不能超出范围
            if (alpha >= 1){
                direction = -1
                alpha = 1f
            }else if (alpha <= 0.2){
                alpha = 0.2f
                direction = 1
            }
        }
    }

    override fun render(time: Long, canvas: Canvas) {
        super.render(time, canvas)

        val paint = Paint()
        paint.textSize = 200f
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        paint.textAlign = Paint.Align.CENTER
        paint.alpha = 255
        //绘制消方块几个字
        canvas.drawText("消砖块", (Director.director.screenWidth / 2).toFloat(), (Director.director.screenHeight / 2).toFloat(), paint)
        paint.textSize = 100f
        paint.color = Color.WHITE
        paint.textAlign = Paint.Align.LEFT
        paint.alpha = (alpha * 255).toInt()
        //绘制点点，会根据alpha和dotCount来显示，看起来就是动态的了
        canvas.drawText("请稍后.....", 0, 3 + dotCount, (Director.director.screenWidth / 2).toFloat(), (Director.director.screenHeight / 2).toFloat() + 200f, paint)

    }
}