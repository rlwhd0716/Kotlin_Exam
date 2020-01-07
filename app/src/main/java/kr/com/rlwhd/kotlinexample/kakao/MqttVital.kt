package kr.com.rlwhd.kotlinexample.kakao

interface MqttVital {
    fun bloodPressure(bp: String)
    fun heartBeat(hb: String)
    fun breathe(br: String)
    fun temperature(tp: String)
    fun spo2(spo2: String)
}