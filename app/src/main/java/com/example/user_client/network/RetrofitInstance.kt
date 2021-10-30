package com.example.user_client.network

import com.example.user_client.service.GetSignInService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//레트로핏 인스턴스를 생성하는 클래스
class RetrofitInstance {
    fun getSignInInstance(): GetSignInService{
        val retrofit = retrofit2.Retrofit
            .Builder()
            .baseUrl(GetSignInService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetSignInService::class.java)
        return retrofit
    }
}