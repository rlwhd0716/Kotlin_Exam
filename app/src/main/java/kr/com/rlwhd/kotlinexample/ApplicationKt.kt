package kr.com.rlwhd.kotlinexample

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.app.ProgressDialog
import android.app.Service
import android.content.Context
import android.content.SharedPreferences

class ApplicationKt : Application() {
    val TAG: String? = this.javaClass.simpleName

    private var pref: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    private var mProgressDialog: ProgressDialog? = null

    @SuppressLint("CommitPrefEdits")
    override fun onCreate() {
        super.onCreate()
        pref = getSharedPreferences(getString(R.string.application), Service.MODE_PRIVATE)
        editor = pref!!.edit()
    }

//    fun getIMEI(): String{
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.READ_PHONE_STATE
//            ) !== PackageManager.PERMISSION_GRANTED
//        ) {
//        }
//        return mTelephonyManager.getDeviceId()
//    }


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