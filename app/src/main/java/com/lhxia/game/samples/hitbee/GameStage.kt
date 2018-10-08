package com.lhxia.game.samples.hitbee

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import com.lhxia.game.core.obj.Director
import com.lhxia.game.core.obj.Spirit
import com.lhxia.game.core.obj.Stage

class GameStage : Stage() {

    private lateinit var blockList : ArrayList<Block>
    private lateinit var boat : Boat
    private lateinit var ball : Ball

    init {
        makeBlocks()
        makeBoat()
        makeBall()
    }

    //球
    private fun makeBall(){
        val w = 30
        val h = 30
        ball = Ball()
        ball.width = w
        ball.height = h
        ball.x = Director.director.screenWidth / 2 - w / 2
        ball.y = boat.y - h
    }

    //底部长条
    private fun makeBoat(){
        val w = blockList[0].width * 3
        val h = w * 2 / 5
        boat = Boat()
        boat.width = w
        boat.height = 60
        boat.x = Director.director.screenWidth / 2 - w / 2
        boat.y = Director.director.screenHeight - h - 30
    }

    private fun makeBlocks(){
        val col = 10
        val row = 3
        val paddingLeftRight = 50
        val marginLeftRight = 10
        val blockCount = col * row
        //居中
        val blockWidth = (Director.director.screenWidth - 2 * paddingLeftRight - (col - 1) * marginLeftRight) / col
        val blockHeight = blockWidth * 3 / 4
        blockList = ArrayList(blockCount)
        var x = paddingLeftRight
        var y = 0
        //绘制二维数组，这里使用一维数组模拟二维数组
        for (i in (0 .. blockCount)){
            val block = Block()
            block.x = x
            block.y = y
            block.width = blockWidth
            block.height = blockHeight
            blockList.add(block)
            x += (marginLeftRight + blockWidth)

            if (i != 0 && i % 10 == 0){
                y += (blockHeight + 10)
            }
            if (i % 10 == 0){
                x = paddingLeftRight
            }

        }
    }


    override fun update(time: Long) {
        super.update(time)
    }

    override fun render(time: Long, canvas: Canvas) {
        super.render(time, canvas)

        blockList.forEach {
            it.render(time, canvas)
        }
        boat.render(time, canvas)
        ball.render(time, canvas)
    }


    //砖块
    class Block : Spirit() {
        override fun render(time: Long, canvas: Canvas) {

            canvas.drawRect(x.toFloat(), y.toFloat(), (x + width).toFloat(), (y + height).toFloat(), Paint().apply {
                color = Color.RED
            })
        }

        override fun update(time: Long) {
        }
    }

    //ball
    class Ball : Spirit() {
        override fun render(time: Long, canvas: Canvas) {
            canvas.drawCircle((x + width / 2).toFloat(), ((y + height / 2).toFloat()), (height / 2).toFloat(), Paint().apply {
                color = Color.RED
            })
        }

        override fun update(time: Long) {
        }
    }

    //boat,底部的那个长条东东
    class Boat : Spirit() {
        override fun render(time: Long, canvas: Canvas) {
            canvas.drawRect(x.toFloat(), y.toFloat(), (x + width).toFloat(), (y + height).toFloat(), Paint().apply {
                color = Color.RED
            })
        }

        override fun update(time: Long) {
        }
    }
}