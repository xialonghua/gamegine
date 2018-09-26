package com.lhxia.game.core

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import com.lhxia.game.core.obj.Director
import com.lhxia.game.core.obj.SampleSpirit
import com.lhxia.game.core.obj.Spirit
import com.lhxia.game.log.debug

class GameLooper(val surfaceView: GameSurfaceView, var director : Director) : Runnable{

    var gameThread : Thread? = null
    @Volatile
    var quite = false

    fun startLoop(){
        if (gameThread != null){
            return
        }
        gameThread = Thread(this).apply {
            quite = true
            start()
        }
    }

    private fun loop(){
        val refreshSeq = (1000 / 40f).toLong()
        var time = 0L
        while (quite){
            time = System.currentTimeMillis()
            logic(time)
            val canvas = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                surfaceView.holder.lockHardwareCanvas()
            } else {
                surfaceView.holder.lockCanvas()
            }
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
            render(time, canvas)

            surfaceView.holder.unlockCanvasAndPost(canvas)
            time = System.currentTimeMillis() - time
            debug("looper coast = $time ms")
            if (time <= refreshSeq){
                Thread.sleep(refreshSeq - time)
            }


        }
    }

    private fun logic(time : Long){

        director.update(time)
    }

    private fun render(time : Long, canvas : Canvas){
        director.render(time, canvas)
    }

    override fun run() {
        loop()
    }

    fun quite(){
        quite = true
        gameThread?.join()
        gameThread = null
    }
}