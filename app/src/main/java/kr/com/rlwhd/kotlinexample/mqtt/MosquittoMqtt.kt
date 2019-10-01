package kr.com.rlwhd.kotlinexample.mqtt

import android.app.Activity
import android.content.Context
import android.util.Log
import kr.com.rlwhd.kotlinexample.adapter.MqttMessageAdapter
import kr.com.rlwhd.kotlinexample.data.MqttData
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*


class MosquittoMqtt(c: Context, activity: Activity) {
    private val TAG: String? = this.javaClass.simpleName

    // 2번째 파라메터 : 브로커의 ip 주소 , 3번째 파라메터 : client 의 id를 지정함 여기서는 paho 의 자동으로 id를 만들어주는것
    val mqttAndroidClient = MqttAndroidClient(c, "tcp://" + "192.168.0.80" + ":1883", MqttClient.generateClientId())
    private var mqttData : MqttData ?= null
    private var msgList = arrayListOf<MqttData>()
    val mAdapter = MqttMessageAdapter(activity, msgList)

    fun initMqtt() {
        try {
            val token = mqttAndroidClient.connect(getMqttConnectionOption())    //mqtttoken 이라는것을 만들어 connect option을 달아줌
            token.actionCallback = object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken) {
                    mqttAndroidClient.setBufferOpts(getDisconnectedBufferOptions())    //연결에 성공한경우
                    Log.e("Connect_success", "Success")

                    try {
                        mqttAndroidClient.subscribe("/TEST", 0)   //연결에 성공하면 /TEST 라는 토픽으로 subscribe함
                    } catch (e: MqttException) {
                        Log.e(TAG, "subscribe Error - $e")
                    }

                }

                override fun onFailure(asyncActionToken: IMqttToken, exception: Throwable) {   //연결에 실패한경우
                    Log.e("connect_fail", "Failure $exception")
                }
            }
        } catch (e: MqttException) {
            e.printStackTrace()
        }

        callBack()
    }

    private fun callBack() {
        mqttAndroidClient.setCallback(object : MqttCallback {  //클라이언트의 콜백을 처리하는부분
            override fun connectionLost(cause: Throwable) {

            }

            @Throws(Exception::class)
            override fun messageArrived(topic: String, message: MqttMessage) {    //모든 메시지가 올때 Callback method
                if (topic == "/TEST") {     //topic 별로 분기처리하여 작업을 수행할수도있음
                    val msg = String(message.payload)

                    mqttData = MqttData(msg)
                    msgList.add(0, mqttData!!)
                    mAdapter.notifyDataSetChanged()

                    Log.e("arri0ve message : ", msg)
                }
            }

            override fun deliveryComplete(token: IMqttDeliveryToken) {

            }
        })
    }

    private fun getDisconnectedBufferOptions(): DisconnectedBufferOptions {
        val disconnectedBufferOptions = DisconnectedBufferOptions()
        disconnectedBufferOptions.isBufferEnabled = true
        disconnectedBufferOptions.bufferSize = 100
        disconnectedBufferOptions.isPersistBuffer = true
        disconnectedBufferOptions.isDeleteOldestMessages = false
        return disconnectedBufferOptions
    }


    private fun getMqttConnectionOption(): MqttConnectOptions {
        val mqttConnectOptions = MqttConnectOptions()
        mqttConnectOptions.isCleanSession = false
        mqttConnectOptions.isAutomaticReconnect = true
        mqttConnectOptions.setWill("aaa", "I am going offline".toByteArray(), 1, true)
        return mqttConnectOptions
    }

}