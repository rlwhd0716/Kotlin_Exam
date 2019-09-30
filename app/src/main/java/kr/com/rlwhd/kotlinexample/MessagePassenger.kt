package kr.com.rlwhd.kotlinexample

import kr.com.rlwhd.kotlinexample.data.MqttData

interface MessagePassenger {
    fun passenger(msg: String){
        val mqttData = MqttData(msg)
        passenger().add(mqttData)
    }

    fun passenger(): ArrayList<MqttData>{
        return passenger()
    }

}