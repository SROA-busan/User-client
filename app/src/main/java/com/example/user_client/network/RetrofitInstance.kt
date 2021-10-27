package com.example.user_client.network

import com.example.user_client.service.GetService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//레트로핏 인스턴스를 생성하는 클래스
class RetrofitInstance {
    fun getRetrofitInstance(): Retrofit{
        val retrofit = retrofit2.Retrofit
            .Builder()
            .baseUrl(GetService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }
}