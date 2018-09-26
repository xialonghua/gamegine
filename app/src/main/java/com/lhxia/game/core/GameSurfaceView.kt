package com.lhxia.game.core

import android.content.Context
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.lhxia.game.core.obj.Director
import com.lhxia.game.log.debug

class GameSurfaceView(director : Director, context: Context) : SurfaceView(context) {

    val looper = GameLooper(this, director)

    init {
        holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
                debug("surfaceChanged")
            }

            override fun surfaceDestroyed(p0: SurfaceHolder?) {
                debug("surfaceDestroyed")
                looper.quite()
            }

            override fun surfaceCreated(p0: SurfaceHolder?) {
                debug("surfaceCreated")
                looper.startLoop()
            }
        })
    }

    init {

//        https@ //www.cnblogs.com/kekec/p/3670389.html
    }



}