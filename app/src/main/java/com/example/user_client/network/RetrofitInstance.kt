package com.example.user_client.network

import com.example.user_client.R
import com.example.user_client.service.GetDataService
import com.example.user_client.service.GetReservationSchedule
import com.example.user_client.service.GetSignInService
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

//레트로핏 인스턴스를 생성하는 클래스
class RetrofitInstance {
    companion object{
        private val BASE_URL = "http://192.168.14.10:"
        private val inquerySchedule = "8000"
        private val signIn = "8001"
        private val reservationSchdule = "8002"
    }
    //회원가입관련 레스토핏 인스턴스
    fun getSignInInstance(): GetSignInService{
        return retrofit2.Retrofit
            .Builder()
            .baseUrl(BASE_URL+signIn)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetSignInService::class.java)

    }
    //정보 로드관련 레트로핏 인스턴스
    fun getData(): GetDataService{
        return retrofit2.Retrofit
            .Builder()
            .baseUrl(BASE_URL+inquerySchedule)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetDataService::class.java)
    }

    //예약정보 조회관련
    fun getReservationSchedule() : GetReservationSchedule {
        return retrofit2.Retrofit
            .Builder()
            .baseUrl(BASE_URL+ reservationSchdule)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetReservationSchedule::class.java)
    }
    //주소검색 서비스
//    fun getMapInstance(): GetJusoService{
//        val retrofit = retrofit2.Retrofit
//            .Builder()
//            .baseUrl(GetJusoService.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(GetJusoService::class.java)
//
//        return retrofit
//    }
}