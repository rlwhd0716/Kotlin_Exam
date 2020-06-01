package kr.com.rlwhd.kotlinexample.retrofit

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kr.com.rlwhd.kotlinexample.retrofit.sample.GisMainIcData
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @Headers("Authorization: KakaoAK 778c04842e44e001414b663621c3c2b6")
    @GET("/v2/search/image")
    fun requestSearchImage(
        @Query("query") keyword: String
    ): Call<ImageData>

    @Headers("Authorization: KakaoAK 778c04842e44e001414b663621c3c2b6")
    @GET("/v2/search/vclip")
    fun requestSearchVideo(
        @Query("query") keyword: String
    ): Call<VideoData>
//    @Query("sort") sort: String = "recency",
//    @Query("page") page: Int,
//    @Query("size") size: Int = 10

    @Headers("accept: application/json", "content-type: application/json")
    @POST("{version}/users/{uid}/friends")
    fun addFriend(
        @Header("apikey") apiKey: String,
        @Header("appid") appID: String,
        @Body params: HashMap<String, List<String>>,
        @Path("version") version: String,
        @Path("uid") uid: String
    ): Call<TestData>


    @Headers("Accept: application/json", "Content-type: text/plain;charset=UTF-8", "Connection: close")
    @POST("scall")
    fun call_GisMainIc(
        @Body params: JsonObject
    ): Call<JsonArray>
}