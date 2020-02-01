package com.belatrix.compass

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.hardware.SensorManager
import android.util.Log
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import kotlinx.android.synthetic.main.compass.*
import java.lang.Math.round

class MainActivity : AppCompatActivity(), SensorEventListener {

    private var currentDegree = 0f
    private lateinit var mSensorManager: SensorManager

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        Log.i("onAccuracyChanged", "onAccuracyChanged")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            val degree = round(event.values[0])
            val ra = RotateAnimation(
                currentDegree,
                -degree.toFloat(),
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
            ra.duration = 210
            ra.fillAfter = true
            imageViewCompass.startAnimation(ra)
            currentDegree = -degree.toFloat()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compass)
        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(
            this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
            SensorManager.SENSOR_DELAY_GAME
        )
    }

    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(this)
    }
}
