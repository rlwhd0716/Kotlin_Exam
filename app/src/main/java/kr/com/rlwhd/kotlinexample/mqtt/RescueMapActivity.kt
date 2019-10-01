package kr.com.rlwhd.kotlinexample.mqtt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_rescue_map.*
import kr.com.rlwhd.kotlinexample.ApplicationKt
import kr.com.rlwhd.kotlinexample.R

class RescueMapActivity : AppCompatActivity() {
    private val TAG: String = this.javaClass.simpleName

    private lateinit var mApplicationKt: ApplicationKt
    private var mosquittoMqtt : MosquittoMqtt ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rescue_map)
        mApplicationKt = application as ApplicationKt
        mosquittoMqtt = MosquittoMqtt(this, this)

        rv_message_rescue.adapter = mosquittoMqtt?.mAdapter
        mosquittoMqtt?.initMqtt() // mqtt 연결

        val lm = LinearLayoutManager(this)
        rv_message_rescue.layoutManager = lm
        rv_message_rescue.setHasFixedSize(true)
    }

}
