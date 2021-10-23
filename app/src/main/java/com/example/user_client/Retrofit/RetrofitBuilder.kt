package com.example.user_client.Retrofit

import com.google.android.gms.common.api.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    var api : API
    init{
        val retrofit= Retrofit.Builder()
            .baseUrl("http://192.168.26.205:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api=retrofit.create(API::class.java)
    }

}