package kr.com.rlwhd.kotlinexample.kakao

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_rescue_map.*
import kr.com.rlwhd.kotlinexample.ApplicationKt
import kr.com.rlwhd.kotlinexample.R
import kr.com.rlwhd.kotlinexample.data.VitalData
import kr.com.rlwhd.kotlinexample.mqtt.MosquittoMqtt
import net.daum.android.map.MapViewEventListener
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class RescueMapActivity : AppCompatActivity(), MapViewEventListener, MapView.POIItemEventListener,
    MapView.OpenAPIKeyAuthenticationResultListener, MqttVital {
    private val TAG: String = this.javaClass.simpleName

    private lateinit var mApplicationKt: ApplicationKt
    private var mosquittoMqtt: MosquittoMqtt? = null
    private var mapView: MapView? = null
    private var mapPOIItem: MapPOIItem? = null
    private val mVitalData = VitalData()

    private val DEFAULT_LOCATION: MapPoint = MapPoint.mapPointWithGeoCoord(0.0,0.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rescue_map)

        mApplicationKt = application as ApplicationKt
        mosquittoMqtt = MosquittoMqtt(c = this, activity = this, mqttVital = this)
        mapView = MapView(this)
        setMap()
        setPoi()

        rl_map_rescue.addView(mapView)

        setMQTT()
    }

    /**
     * 맵 셋팅
     */
    private fun setMap() {
        mapView!!.mapViewEventListener = this
        mapView!!.setPOIItemEventListener(this)
        mapView!!.mapType = MapView.MapType.Standard
        mapView!!.setMapCenterPointAndZoomLevel(DEFAULT_LOCATION, 5, true) // 중심점 변경 + 줌 레벨 변경
        mapView!!.zoomIn(true) // 줌 인
        mapView!!.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading

//        mapView!!.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(DEFAULT_LOCATION), true) // 중심점 변경
//        mapView!!.setZoomLevel(7, true) // 줌 레벨 변경
//        mapView!!.zoomOut(true) // 줌 아웃
    }

    override fun spo2(spo2: String) {
        tv_SpO2.text = "$spo2 %"
    }
    override fun heartBeat(hb: String) {
        tv_heart_beat.text = "$hb bpm"
    }

    override fun breathe(br: String) {
        tv_breathe.text = "$br 회/분"
    }

    override fun temperature(tp: String) {
        tv_temperature.text = "$tp ℃"
    }

    override fun bloodPressure(bp: String) {
        tv_blood_pressure.text = "$bp mmHg"
    }

    /**
     * POI 셋팅, 커스텀 이미지 사용가능
     */
    private fun setPoi() {
        mapPOIItem = MapPOIItem()
        mapPOIItem?.itemName = "대구공업대학교"
        mapPOIItem?.tag = 0
        mapPOIItem?.mapPoint = DEFAULT_LOCATION
        mapPOIItem?.markerType = MapPOIItem.MarkerType.BluePin
        mapPOIItem?.selectedMarkerType = MapPOIItem.MarkerType.RedPin

        mapView?.setCalloutBalloonAdapter(CustomBalloonAdapter(this))
        mapView?.addPOIItem(mapPOIItem)
        mapView?.selectPOIItem(mapPOIItem, true)
        mapView?.setMapCenterPoint(DEFAULT_LOCATION, false)
    }

    /**
     * MQTT 셋팅
     */
    private fun setMQTT() {
        rv_message_rescue.adapter = mosquittoMqtt?.mAdapter
        mosquittoMqtt?.initMqtt() // mqtt 연결

        val lm = LinearLayoutManager(this)
        rv_message_rescue.layoutManager = lm
        rv_message_rescue.setHasFixedSize(true)
    }

    override fun onDestroy() {
        super.onDestroy()

        mosquittoMqtt?.mqttAndroidClient?.unregisterResources()
        mosquittoMqtt?.mqttAndroidClient?.close()
    }

    /**
     * 맵 인터페이스
     */
    override fun onLoadMapView() {
    }

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {
    }

    override fun onCalloutBalloonOfPOIItemTouched(
        p0: MapView?,
        p1: MapPOIItem?,
        p2: MapPOIItem.CalloutBalloonButtonType?
    ) {
    }

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {
    }

    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
    }

    override fun onDaumMapOpenAPIKeyAuthenticationResult(p0: MapView?, p1: Int, p2: String?) {
    }
}
