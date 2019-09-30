package kr.com.rlwhd.kotlinexample.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_stop_watch.*
import kr.com.rlwhd.kotlinexample.R
import java.util.*
import kotlin.concurrent.timer

class StopWatchActivity : AppCompatActivity() {
    private var time = 0
    private var timerTask: Timer? = null
    private var isRunning = false
    private var lap = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stop_watch)


        fab_play.setOnClickListener {
            isRunning = !isRunning

            if (isRunning) {
                start()
            } else {
                pause()
            }
        }

        bt_labtime.setOnClickListener {
            recordLapTime()
        }
    }
    private fun recordLapTime() {
        val lapTime = this.time
        val textView = TextView(this)
        textView.text = "$lap LAB : ${lapTime / 100}.${lapTime % 100}"

        sv_labtime.addView(textView, 0)
        lap++
    }

    private fun start() {
        fab_play.setImageResource(R.drawable.ic_pause_black_24dp)

        timerTask = timer(period = 10) {
            time++
            val sec = time / 100
            val milli = time % 100
            runOnUiThread {
                tv_second.text = "$sec"
                tv_millis.text = "$milli"
            }
        }
    }

    private fun pause() {
        fab_play.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        timerTask?.cancel()
    }
}
