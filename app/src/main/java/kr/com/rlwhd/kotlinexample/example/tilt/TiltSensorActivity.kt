package kr.com.rlwhd.kotlinexample.example.tilt

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.sqrt

class TiltSensorActivity : AppCompatActivity(), SensorEventListener {
    private val TAG: String = this.javaClass.simpleName
    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
    private lateinit var tiltView: TiltView

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        super.onCreate(savedInstanceState)

        tiltView = TiltView(this)
        setContentView(tiltView)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            Log.d(TAG, "onSensorChanged - x : ${event.values[0]}, y : ${event.values[1]}, z : ${event.values[2]}")

            Log.e(
                TAG, "평균 - ${sqrt(
                    event.values[0].toDouble().pow(2.toDouble()) +
                            event.values[1].toDouble().pow(2.toDouble()) +
                            event.values[2].toDouble().pow(2.toDouble())
                )}"
            )
            tiltView.onSensorEvent(event)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}
