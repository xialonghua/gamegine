package com.lhxia.game.core.obj

import android.graphics.Canvas

abstract class Spirit {

    open var x = 0
    open var y = 0
    var width = 0
    var height = 0

    abstract fun render(time : Long, canvas : Canvas)

    abstract fun update(time : Long)
}