package com.example.user_client.Retrofit

import retrofit2.Call;
import retrofit2.http.GET
import retrofit2.http.Path

public interface API {
    @GET("/account/login/{ID}/{PW}")
    fun login(@Path("ID") id:String, @Path("PW") pw:String):Call<Int>
}