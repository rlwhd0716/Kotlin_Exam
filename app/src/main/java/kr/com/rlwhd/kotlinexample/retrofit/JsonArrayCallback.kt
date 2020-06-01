package kr.com.rlwhd.kotlinexample.retrofit

import android.util.Log
import com.google.gson.JsonArray
import kr.com.rlwhd.kotlinexample.retrofit.sample.GisMainIcData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JsonArrayCallback : Callback<JsonArray>{
    private val mArrayList = ArrayList<Any>()
    override fun onFailure(call: Call<JsonArray>, t: Throwable) {
        Log.e(SearchRetrofit.TAG, "API call 실패: $call \nexception: $t")
    }

    override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
        Log.e("Response:: ", response.body().toString())

        val mJsonArray = response.body()!!
        for (json in mJsonArray){
            val mData = GisMainIcData(json.asJsonObject["MAINIC_DESC"], json.asJsonObject["GIS_X"], json.asJsonObject["GIS_Y"])
            mArrayList.add(mData)
        }

//        for (array in mArrayList) {
//            Log.e("Callback", "data - $array")
//        }
    }

    fun getServiceData() : ArrayList<Any>  = mArrayList

}