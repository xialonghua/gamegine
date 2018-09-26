package com.lhxia.game.core.obj

import android.graphics.Canvas

open class Stage : Spirit {

    val spirits : ArrayList<Spirit> = ArrayList()

    override fun render(time: Long, canvas: Canvas) {
        spirits.forEach {
            it.render(time, canvas)
        }
    }

    override fun update(time: Long) {
        spirits.forEach {
            it.update(time)
        }
    }
}