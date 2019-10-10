package kr.com.rlwhd.kotlinexample;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import com.kakao.util.maps.helper.Utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class KeyHash {
    private String TAG = this.getClass().getSimpleName();

     public String getKeyHash(final Context context) {
      PackageInfo packageInfo = Utility.getPackageInfo(context, PackageManager.GET_SIGNATURES);
      if (packageInfo == null)
          return null;
      for (Signature signature : packageInfo.signatures) {
          try {
              MessageDigest md = MessageDigest.getInstance("SHA");
              md.update(signature.toByteArray());

              return Base64.encodeToString(md.digest(), Base64.NO_WRAP);
          } catch (NoSuchAlgorithmException e) {
              Log.e(TAG, "디버그 keyHash" + signature, e);
          }
      }
      return null;
  }
}
