package kr.com.rlwhd.kotlinexample

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.util.Base64
import android.util.Log
import com.kakao.util.maps.helper.Utility

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class KeyHash {
    private val TAG = this.javaClass.simpleName

    fun getKeyHash(context: Context): String? {
        val packageInfo = Utility.getPackageInfo(context, PackageManager.GET_SIGNATURES) ?: return null
        for (signature in packageInfo.signatures) {
            try {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())

                return Base64.encodeToString(md.digest(), Base64.NO_WRAP)
            } catch (e: NoSuchAlgorithmException) {
                Log.e(TAG, "디버그 keyHash$signature", e)
            }

        }
        return null
    }
}
