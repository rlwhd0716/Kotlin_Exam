package kr.com.rlwhd.kotlinexample.retrofit.java;

import kr.com.rlwhd.kotlinexample.retrofit.TestData;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitService {
    @Headers({"accept: application/json", "content-type: application/json"})
    @POST("{version}/users/{uid}/friends")
    Call<TestData> addService(
            @Header("apikey") String apiKey
    );
}
