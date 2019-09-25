package kr.com.rlwhd.kotlinexample

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import kr.com.rlwhd.kotlinexample.example.Animal
import kr.com.rlwhd.kotlinexample.example.Dog
import kr.com.rlwhd.kotlinexample.example.Person
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    var str: String = "버튼을 눌렀습니다."
    private lateinit var v: View
    private val dog = Dog("")
    private val person = Person("")
    private var animal = Animal("동물")
    private var mIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermissions()

        textView.text = animal.name
        onClickEvent()

    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
            0
        )
    }

    private fun onClickEvent() {
        bt_change.setOnClickListener {
            dog.run("강아지")
            textView.text = dog.name
            Log.e("111", "")
        }

        bt_fat.setOnClickListener {
            startActivity<FatCalcActivity>()
        }

        bt_stopwatch.setOnClickListener {
            startActivity<StopWatchActivity>()
        }

        bt_tilt.setOnClickListener {
//            mIntent = Intent(this, TiltSensorActivity::class.java)
//            startActivity(mIntent)
            startActivity<TiltSensorActivity>()
        }

        bt_video.setOnClickListener {
            startActivity<VideoPlayActivity>()
        }
    }


}

