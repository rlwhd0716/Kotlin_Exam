package kr.com.rlwhd.kotlinexample.kakao

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kr.com.rlwhd.kotlinexample.R
import net.daum.mf.map.api.CalloutBalloonAdapter
import net.daum.mf.map.api.MapPOIItem

class CustomPoiBalloon(context: Context) : CalloutBalloonAdapter {
    private var mBalloon: View? = null

    init {
        mBalloon = View.inflate(context, R.layout.custom_ballon, null)
    }

    override fun getPressedCalloutBalloon(p0: MapPOIItem?): View? {
        mBalloon?.findViewById<ImageView>(R.id.poi_balloon_badge)?.setImageResource(R.drawable.ic_launcher)
        mBalloon?.findViewById<TextView>(R.id.poi_balloon_title)?.text = p0?.itemName
        mBalloon?.findViewById<TextView>(R.id.poi_balloon_desc)?.text = ""
        return mBalloon
    }

    override fun getCalloutBalloon(p0: MapPOIItem?): View? {
        return null
    }
}