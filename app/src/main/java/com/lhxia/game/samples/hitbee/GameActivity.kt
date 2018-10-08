package com.lhxia.game.samples.hitbee

import android.graphics.Point
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.lhxia.game.core.GameLooper
import com.lhxia.game.core.GameSurfaceView
import com.lhxia.game.core.obj.Director
import com.lhxia.game.core.obj.SampleSpirit
import com.lhxia.game.core.obj.Stage

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(GameSurfaceView(Director.director,this), ViewGroup.LayoutParams(-1, -1))

        val out = Point()
        windowManager.defaultDisplay.getSize(out)

        Director.director.screenWidth = out.x
        Director.director.screenHeight = out.y
        Director.director.changeStage(StartStage())
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}