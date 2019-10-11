package kr.com.rlwhd.kotlinexample.kakao

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kr.com.rlwhd.kotlinexample.R
import net.daum.mf.map.api.CalloutBalloonAdapter
import net.daum.mf.map.api.MapPOIItem

class CustomBalloonAdapter(val context: Context) : CalloutBalloonAdapter {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val mCalloutBalloon: View = inflater.inflate(R.layout.custom_ballon, null)

    override fun getCalloutBalloon(poiItem: MapPOIItem): View {
        mCalloutBalloon.findViewById<ImageView>(R.id.poi_balloon_badge).setImageResource(R.drawable.ic_launcher)
        mCalloutBalloon.findViewById<TextView>(R.id.poi_balloon_title).text = poiItem.itemName
        mCalloutBalloon.findViewById<TextView>(R.id.poi_balloon_desc).text = "2층"
        return mCalloutBalloon
    }

    override fun getPressedCalloutBalloon(poiItem: MapPOIItem): View? {
        return null
    }
}