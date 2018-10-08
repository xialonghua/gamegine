package com.lhxia.game.core.obj

import android.graphics.Rect

//每个精灵都有一个渲染的区域，叫做box。除了渲染还有其他用处
class Box {

    /**
     * 所占的区域
     */
    val rect : Rect = Rect()

    var x : Int = 0
    var y : Int = 0
}