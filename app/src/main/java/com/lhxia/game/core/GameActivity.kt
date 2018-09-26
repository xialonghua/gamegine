package com.lhxia.game.core

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.lhxia.game.core.obj.Director
import com.lhxia.game.core.obj.SampleSpirit
import com.lhxia.game.core.obj.Stage

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(GameSurfaceView(Director().apply {
            stage = Stage().apply {
                for (i in 0 .. 1000){
                    spirits.add(SampleSpirit())
                }
            }
        },this), ViewGroup.LayoutParams(-1, -1))



    }

}