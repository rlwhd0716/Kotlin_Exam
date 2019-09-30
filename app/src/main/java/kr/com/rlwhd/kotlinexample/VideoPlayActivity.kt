package kr.com.rlwhd.kotlinexample

import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_video_play.*
import kr.com.rlwhd.kotlinexample.adapter.VideoPlayAdapter
import kr.com.rlwhd.kotlinexample.data.MqttData
import org.eclipse.paho.client.mqttv3.MqttException
import org.jetbrains.anko.toast

class VideoPlayActivity : AppCompatActivity(), MessagePassenger {
    private val TAG: String? = this.javaClass.simpleName

    private var mApplicationKt: ApplicationKt? = null
    private val mosquittoMqtt = MosquittoMqtt(this)
    //    private var msgList = arrayListOf<MqttData>(MqttData("나오나?"), MqttData("잘돼??"))
    private var msgList = arrayListOf<MqttData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_play)
        mApplicationKt = application as ApplicationKt

        val mAdapter = VideoPlayAdapter(this, msgList)
        rv_message.adapter = mAdapter

        val lm = LinearLayoutManager(this)
        rv_message.layoutManager = lm
        rv_message.setHasFixedSize(true)

        videoPlayer()
        mosquittoMqtt.initMqtt()


        tv_play.setOnClickListener {
            try {
                //버튼을 클릭하면 /TEST 라는 토픽으로 메시지를 보냄
                mosquittoMqtt.mqttAndroidClient.publish("/TEST", "클라 테스트!!!!".toByteArray(), 0, false)

//                msgList = passenger()
//                mAdapter.notifyDataSetChanged()
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
        mApplicationKt!!.startProgress(this, "")

        vv_player.setOnPreparedListener {
            mApplicationKt!!.stopProgress()
        }

        vv_player.setOnCompletionListener {
            vv_player.start()
            toast("재생완료")
        }

    }
}
