package kr.com.rlwhd.kotlinexample.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.get
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_admob.*
import kr.com.rlwhd.kotlinexample.R

class AdmobActivity : AppCompatActivity() {
    val TAG = this.javaClass.simpleName
    private lateinit var mInterstitialAd: InterstitialAd
    private lateinit var mTapArray: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admob)
        MobileAds.initialize(this, getString(R.string.admob_app_id))

        setTab()
    }

    private fun setTab() {
        mTapArray = resources.getStringArray(R.array.customtargeting_sports)
        for (i in mTapArray) tl_admob.addTab(tl_admob.newTab().setText(i))
        tl_admob.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                setAdView(mTapArray[tab.position])
            }
        })
    }

    private fun setAdView(category: String) {
        val ft = this.supportFragmentManager.beginTransaction()
        val mFragment = AdMobCustomMuteThisAdFragment()
        ft.replace(R.id.container, mFragment).commitNowAllowingStateLoss()
        
        val adRequest = PublisherAdRequest.Builder()
            .addCustomTargeting(
                getString(R.string.customtargeting_key),
                category
            )
            .build()

//        customtargeting_av_main.loadAd(adRequest)
    }

}
