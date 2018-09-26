package com.lhxia.game.core.obj

import android.graphics.Canvas

class Director : Spirit {

    var screenWidth : Int = 0
    var screenHeight : Int = 0

    var stage : Stage? = null
    var oldStage : Stage? = null

    override fun render(time: Long, canvas: Canvas) {
        stage?.render(time, canvas)
        oldStage?.render(time, canvas)
    }

    override fun update(time: Long) {
        stage?.update(time)
        oldStage?.update(time)
    }

    companion object {
        val director : Director = Director()
    }
}