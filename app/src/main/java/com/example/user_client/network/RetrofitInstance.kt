package com.example.user_client.network

import com.example.user_client.R
import com.example.user_client.service.GetDataService
import com.example.user_client.service.GetReservationSchedule
import com.example.user_client.service.GetSignInService
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

//레트로핏 인스턴스를 생성하는 클래스
class RetrofitInstance {
    companion object{
//        private val BASE_URL = "http://3.36.108.92:"
//        private val BASE_URL = "http://192.168.162.242:"
        private val BASE_URL = "http://192.168.0.4:"
        private val inquerySchedule = "8084"//8084
        private val signIn = "8081"
        private val reservationSchdule = "8002"
    }

    fun setOkHttp(): OkHttpClient{
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor).build()
        return client
    }

    //회원가입관련 레스토핏 인스턴스
    fun getSignInInstance(): GetSignInService{

        return retrofit2.Retrofit
            .Builder()
            .baseUrl(BASE_URL+signIn)
            .addConverterFactory(GsonConverterFactory.create())
            .client(setOkHttp())
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