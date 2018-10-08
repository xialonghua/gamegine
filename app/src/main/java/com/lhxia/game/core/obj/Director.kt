package com.lhxia.game.core.obj

import android.graphics.Canvas

class Director : Spirit() {

    var screenWidth : Int = 0
    var screenHeight : Int = 0

    private var stage : Stage? = null
    private var oldStage : Stage? = null

    override fun render(time: Long, canvas: Canvas) {
        val s = stage
        s?.render(time, canvas)
//        oldStage?.render(time, canvas)
    }

    override fun update(time: Long) {
        stage?.update(time)
        oldStage?.update(time)
    }

    fun changeStage(stage: Stage){
        this.stage = stage
        this.oldStage = null
    }

    companion object {
        val director : Director = Director()
    }
}