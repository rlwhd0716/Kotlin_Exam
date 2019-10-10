package kr.com.rlwhd.kotlinexample.mqtt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_rescue_map.*
import kr.com.rlwhd.kotlinexample.ApplicationKt
import kr.com.rlwhd.kotlinexample.R
import kr.com.rlwhd.kotlinexample.kakao.CustomPoiBalloon
import net.daum.android.map.MapViewEventListener
import net.daum.mf.map.api.MapLayout
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class RescueMapActivity : AppCompatActivity(), MapViewEventListener, MapView.POIItemEventListener,
    MapView.OpenAPIKeyAuthenticationResultListener {
    private val TAG: String = this.javaClass.simpleName

    private lateinit var mApplicationKt: ApplicationKt
    private var mosquittoMqtt: MosquittoMqtt? = null
    private var mapView: MapView? = null
    private var mapPOIItem: MapPOIItem? = null
//    private var mBalloon: CustomPoiBalloon? = null

    private val DEFAULT_LOCATION: MapPoint = MapPoint.mapPointWithGeoCoord(0.0, 0.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rescue_map)

        mApplicationKt = application as ApplicationKt
        mosquittoMqtt = MosquittoMqtt(this, this)
        mapView = MapView(this)
//        mBalloon = CustomPoiBalloon(this)
        setMap()
        setPoi()

        rl_map_rescue.addView(mapView)

        setMQTT()
    }

    private fun setMap() {
//        mapView!!.setDaumMapApiKey(DAUM_MAPS_ANDROID_APP_API_KEY)
//        mapView!!.setOpenAPIKeyAuthenticationResultListener(this)
        mapView!!.mapViewEventListener = this
        mapView!!.setPOIItemEventListener(this)
        mapView!!.mapType = MapView.MapType.Standard
        mapView!!.setMapCenterPointAndZoomLevel(
            DEFAULT_LOCATION,
            5,
            true
        ) // 중심점 변경 + 줌 레벨 변경

//        mapView!!.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(y,x), true) // 중심점 변경
//        mapView!!.setZoomLevel(7, true) // 줌 레벨 변경

        mapView!!.zoomIn(true) // 줌 인
//        mapView!!.zoomOut(true) // 줌 아웃
    }

    private fun setPoi() {
        mapPOIItem = MapPOIItem()
        mapPOIItem?.itemName = "대구공업대학교"
        mapPOIItem?.tag = 0
        mapPOIItem?.mapPoint = DEFAULT_LOCATION
        mapPOIItem?.markerType = MapPOIItem.MarkerType.BluePin
        mapPOIItem?.selectedMarkerType = MapPOIItem.MarkerType.RedPin

        mapView?.setCalloutBalloonAdapter(CustomPoiBalloon(this))
        mapView?.addPOIItem(mapPOIItem)
        mapView?.selectPOIItem(mapPOIItem, true)
        mapView?.setMapCenterPoint(DEFAULT_LOCATION, false)
    }

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
