package kr.com.rlwhd.kotlinexample

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_video_play.*
import org.jetbrains.anko.toast

class VideoPlayActivity : AppCompatActivity() {
    private var mProgressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_play)

        videoPlayer()
    }

    private fun videoPlayer() {
        var mediaController = MediaController(this)
        vv_player.setVideoPath("rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov")
        vv_player.setMediaController(mediaController)
        vv_player.start()
        startProgress(this, "")

        vv_player.setOnPreparedListener {
            stopProgress()
        }

        vv_player.setOnCompletionListener {
            stopProgress()
            toast("재생완료")
        }

    }

    private fun startProgress(context: Context?, message: String) {
        if (context != null && !(context as Activity).isFinishing) {
            mProgressDialog?.dismiss()
            mProgressDialog = null

            mProgressDialog = ProgressDialog(context)
            mProgressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)

            if ("" == message) {
                mProgressDialog!!.setMessage("잠시만 기다려 주세요...")
            } else {
                mProgressDialog!!.setMessage(message)
            }

            mProgressDialog!!.setCancelable(false)
            mProgressDialog!!.show()
        }
    }

    private fun stopProgress() {
        mProgressDialog?.dismiss()
        mProgressDialog = null
    }
}
