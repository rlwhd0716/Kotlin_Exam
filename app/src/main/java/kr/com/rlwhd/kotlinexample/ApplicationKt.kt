package kr.com.rlwhd.kotlinexample

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.app.ProgressDialog
import android.app.Service
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.provider.Settings
import android.telephony.TelephonyManager
import androidx.core.app.ActivityCompat
import io.realm.Realm
import kr.com.rlwhd.kotlinexample.dgmvp.di.component.AppComponent
import kr.com.rlwhd.kotlinexample.dgmvp.di.component.DaggerAppComponent
import kr.com.rlwhd.kotlinexample.dgmvp.di.module.AppModule
import kr.com.rlwhd.kotlinexample.dgmvp.di.module.NetworkModule
import kr.com.rlwhd.kotlinexample.dgmvp.di.module.PreferenceModule

class ApplicationKt : Application() {
    val TAG: String? = this.javaClass.simpleName

    private var pref: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    private var mProgressDialog: ProgressDialog? = null
    private val mTelephonyManager: TelephonyManager? = null

    val singleton: AppComponent by lazy {
        DaggerAppComponent.builder()
            .networkModule(NetworkModule())
            .preferenceModule(PreferenceModule())
            .appModule(AppModule(this))
            .build()
    }

    @SuppressLint("CommitPrefEdits")
    override fun onCreate() {
        super.onCreate()
        pref = getSharedPreferences(getString(R.string.application), Service.MODE_PRIVATE)
        editor = pref!!.edit()
        Realm.init(this)
    }

    companion object {
        fun getAppComponent(context: Context): AppComponent {
            return (context.applicationContext as ApplicationKt).singleton
        }
    }

    @SuppressLint("HardwareIds")
    fun getIMEI(): String {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) !== PackageManager.PERMISSION_GRANTED
        ) {
        }
        return Settings.Secure.getString(applicationContext.contentResolver, Settings.Secure.ANDROID_ID)
    }


    /**
     * progress
     * Created by rlwhd0716 on 2019-09-25.
     */
    fun startProgress(context: Context?, message: String) {
        if (context != null && !(context as Activity).isFinishing) {
            mProgressDialog?.dismiss()
            mProgressDialog = null

            mProgressDialog = ProgressDialog(context)
            mProgressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)

            if ("" == message) {
                mProgressDialog!!.setMessage("잠시만 기다려 주세요...")
            } else {
                mProgressDialog!!.setMessage(message)
            }

            mProgressDialog!!.setCancelable(true)
            mProgressDialog!!.show()
        }
    }

    fun stopProgress() {
        mProgressDialog?.dismiss()
        mProgressDialog = null
    }

}