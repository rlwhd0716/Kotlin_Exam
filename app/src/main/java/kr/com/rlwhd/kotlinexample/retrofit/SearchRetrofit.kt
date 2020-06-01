package kr.com.rlwhd.kotlinexample.retrofit

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object SearchRetrofit {
    val TAG = javaClass.simpleName

    private val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    private var gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit =
        Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getService(): RetrofitService = retrofit.create(RetrofitService::class.java)

    fun setImage(keyword: String) = getService().requestSearchImage(keyword = keyword)

    fun setVideo(keyword: String) = getService().requestSearchVideo(keyword = keyword)

    fun setFriend() {
        //Body에 담을 데이터 생성
        val friends = ArrayList<String>()
        friends.add("")
        val body = HashMap<String, List<String>>()
        body["accepted"] = friends

        getService().addFriend(
            apiKey = "",
            appID = "",
            params = body,
            version = "",
            uid = ""
        ).enqueue(object : Callback<TestData> {
            override fun onFailure(call: Call<TestData>, t: Throwable) {
                Log.e(TAG, "Failed API call with call: $call exception: $t")
            }

            override fun onResponse(call: Call<TestData>, response: Response<TestData>) {
                Log.d("Response:: ", response.body().toString())
                val friends = response.body()!!.data.accepted

                Log.d("Friends:: ", friends.toString())
                for (friendName in friends.keys) {
                    Log.d("${friendName}:: ", friends[friendName].toString())
                }
            }
        })
    }

    private val testRetrofit =
        Retrofit.Builder()//TODO
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
//            .baseUrl("http://192.168.15.56:3001/crawling/")

    fun getTestService(): RetrofitService = testRetrofit.create(RetrofitService::class.java)

    fun service_GisMainIc(IMEI: String, LOGIN_CAR_ID: String) {
        var mJsonArrayCallback = JsonArrayCallback()
        val body = JsonObject()
        body.addProperty("", "")
        body.addProperty("", IMEI)
        body.addProperty("", LOGIN_CAR_ID)
        Log.e(TAG, "body = $body")
        getTestService().call_GisMainIc(params = body)
            .enqueue(mJsonArrayCallback)

    }
}