package kr.com.rlwhd.kotlinexample.mqtt

import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_video_play.*
import kr.com.rlwhd.kotlinexample.ApplicationKt
import kr.com.rlwhd.kotlinexample.R
import org.eclipse.paho.client.mqttv3.MqttException
import org.jetbrains.anko.toast

class VideoPlayActivity : AppCompatActivity() {
    private val TAG: String? = this.javaClass.simpleName

    private lateinit var mApplicationKt: ApplicationKt
    private var mosquittoMqtt : MosquittoMqtt ?= null
    //    private var msgList = arrayListOf<MqttData>(MqttData("나오나?"), MqttData("잘돼??"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_play)
        mApplicationKt = application as ApplicationKt
        mosquittoMqtt = MosquittoMqtt(this, this)

        videoPlayer()
        rv_message_video.adapter = mosquittoMqtt?.mAdapter
        mosquittoMqtt?.initMqtt() // mqtt 연결

        val lm = LinearLayoutManager(this)
        rv_message_video.layoutManager = lm
        rv_message_video.setHasFixedSize(true)

        tv_play.setOnClickListener {
            try {
                //버튼을 클릭하면 /TEST 라는 토픽으로 메시지를 보냄
                mosquittoMqtt?.mqttAndroidClient?.publish("/TEST", "Client Test!!!!".toByteArray(), 0, false)
            } catch (e: MqttException) {
                Log.e(TAG, "MqttException - ")
            }
        }
    }

    private fun videoPlayer() {
        val mediaController = MediaController(this)
        vv_player.setVideoPath("rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov")
        vv_player.setMediaController(mediaController)
        vv_player.start()
        mApplicationKt.startProgress(this, "")

        vv_player.setOnPreparedListener {
            mApplicationKt.stopProgress()
        }

        vv_player.setOnCompletionListener {
            vv_player.start()
            toast("재생완료")
        }
    }
}
