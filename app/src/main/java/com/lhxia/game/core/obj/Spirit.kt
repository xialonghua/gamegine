package com.lhxia.game.core.obj

import android.graphics.Canvas

interface Spirit {

    fun render(time : Long, canvas : Canvas)

    fun update(time : Long)
}