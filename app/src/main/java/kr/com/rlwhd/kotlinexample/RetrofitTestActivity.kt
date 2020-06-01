package kr.com.rlwhd.kotlinexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_retrofit_test.*
import kr.com.rlwhd.kotlinexample.retrofit.JsonArrayCallback
import kr.com.rlwhd.kotlinexample.retrofit.SearchRetrofit

class RetrofitTestActivity : AppCompatActivity() {
    val TAG = javaClass.simpleName
    val mJsonArrayCallback = JsonArrayCallback()

    val retrofit = SearchRetrofit
    lateinit var mApplicationKt: ApplicationKt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_test)

        mApplicationKt = application as ApplicationKt

        retrofit.service_GisMainIc(mApplicationKt.getIMEI(), "1000000704")
        bt_retrofit_call.setOnClickListener {
            for (i in mJsonArrayCallback.getServiceData()) {
                Log.e(TAG, mJsonArrayCallback.getServiceData().toString())
            }
        }
    }
}
