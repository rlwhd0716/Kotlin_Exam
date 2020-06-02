package kr.com.rlwhd.kotlinexample

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.kakao.util.maps.helper.Utility
import kotlinx.android.synthetic.main.activity_main.*
import kr.com.rlwhd.kotlinexample.dgmvp.dagger.DaggerActivity
import kr.com.rlwhd.kotlinexample.dgmvp.mvp.MvpActivity
import kr.com.rlwhd.kotlinexample.example.*
import kr.com.rlwhd.kotlinexample.example.flash.FlashlightActivity
import kr.com.rlwhd.kotlinexample.example.gallery.MyGalleryActivity
import kr.com.rlwhd.kotlinexample.example.tilt.TiltSensorActivity
import kr.com.rlwhd.kotlinexample.example.todo.TodoListActivity
import kr.com.rlwhd.kotlinexample.kakao.RescueMapActivity
import kr.com.rlwhd.kotlinexample.mqtt.VideoPlayActivity
import kr.com.rlwhd.kotlinexample.retrofit.RetrofitService
import kr.com.rlwhd.kotlinexample.retrofit.SearchRetrofit
import kr.com.rlwhd.kotlinexample.scanner.ScannerListActivity
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    private val TAG: String = this.javaClass.simpleName

    var str: String = "버튼을 눌렀습니다."
    private lateinit var v: View
    private val dog = Dog("")
    private val person = Person("")
    private var animal = Animal("동물")
    private var mIntent: Intent? = null
    private var mApplicationKt: ApplicationKt? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mApplicationKt = application as ApplicationKt

        requestPermissions()

        textView.text = animal.name
        onClickEvent()
        val hash = Utility.getKeyHash(this)

        Log.e(TAG, "keyHash : $hash")
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
            0
        )
    }

    private fun onClickEvent() {
        bt_change.setOnClickListener(onClickListener)
        bt_fat.setOnClickListener(onClickListener)
        bt_stopwatch.setOnClickListener(onClickListener)
        bt_tilt.setOnClickListener(onClickListener)
        bt_video.setOnClickListener(onClickListener)
        bt_kakao_map.setOnClickListener(onClickListener)
        bt_gallery.setOnClickListener(onClickListener)
        bt_clap369.setOnClickListener(onClickListener)
        bt_flashlight.setOnClickListener(onClickListener)
        bt_example.setOnClickListener(onClickListener)
        bt_get_photo_camera.setOnClickListener(onClickListener)
        bt_mvp_exam.setOnClickListener(onClickListener)
        bt_dagger_exam.setOnClickListener(onClickListener)
        bt_ad_exam.setOnClickListener(onClickListener)
        bt_retrofit_exam.setOnClickListener(onClickListener)
        bt_scanner_exam.setOnClickListener(onClickListener)
    }

    private var onClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.bt_change -> changeAnimal()
            R.id.bt_fat -> startActivity<FatCalcActivity>()
            R.id.bt_stopwatch -> startActivity<StopWatchActivity>()
            R.id.bt_tilt -> startActivity<TiltSensorActivity>()
            R.id.bt_video -> startActivity<VideoPlayActivity>()
            R.id.bt_kakao_map -> startActivity<RescueMapActivity>()
            R.id.bt_gallery -> startActivity<MyGalleryActivity>()
            R.id.bt_clap369 -> startActivity<GameActivity>()
            R.id.bt_flashlight -> startActivity<FlashlightActivity>()
            R.id.bt_example -> startActivity<TodoListActivity>()
            R.id.bt_get_photo_camera -> startActivity<GetImageActivity>()
            R.id.bt_mvp_exam -> startActivity<MvpActivity>()
            R.id.bt_dagger_exam -> startActivity<DaggerActivity>()
            R.id.bt_ad_exam -> startActivity<AdmobActivity>()
            R.id.bt_retrofit_exam -> startActivity<RetrofitTestActivity>()
            R.id.bt_scanner_exam -> startActivity<ScannerListActivity>()
            else -> return@OnClickListener
        }
    }

    private fun changeAnimal() {
        if (textView.text === "강아지") {
            textView.text = animal.name
            Log.e("111", "name = ${animal.name}")
        } else {
            dog.run("강아지")
            textView.text = dog.name
            Log.e("111", "name = ${dog.name}")
        }
    }


}

