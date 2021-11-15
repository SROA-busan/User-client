package com.example.user_client.network

import com.example.user_client.service.GetInquiryService
import com.example.user_client.service.GetReservationSchedule
import com.example.user_client.service.GetSignInService
import com.example.user_client.service.PutEvaluation
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//레트로핏 인스턴스를 생성하는 클래스
class RetrofitInstance {
    companion object{
//        private val BASE_URL = "http://3.36.122.237:"
        private val BASE_URL = "http://192.168.0.4:"

        private val accountBASE_URL = "http://13.125.26.25:"
        private val reserveBASE_URL = "http://13.124.169.12:"
        private val inquiryBASE_URL = "http://3.34.182.42:"
        private val evalutionBASE_URL = "http://3.36.113.1:"

        private val ACCOUNT = "8081"
        private val RESERVATION_SCHEDULE = "8083"
        private val INQUERY_SCHEDULE = "8084"//8084
        private val EVALUATION = "8085"
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
            .baseUrl(BASE_URL+ACCOUNT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(setOkHttp())
            .build()
            .create(GetSignInService::class.java)
    }
    //예약정보 조회관련
    fun getReservationSchedule() : GetReservationSchedule {
        return retrofit2.Retrofit
            .Builder()
            .baseUrl(BASE_URL+ RESERVATION_SCHEDULE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetReservationSchedule::class.java)
    }
    //정보 로드관련 레트로핏 인스턴스
    fun getData(): GetInquiryService{
        return retrofit2.Retrofit
            .Builder()
            .baseUrl(BASE_URL+INQUERY_SCHEDULE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetInquiryService::class.java)
    }


    //평가 입력
    fun putEvaluation(): PutEvaluation{
        return retrofit2.Retrofit
            .Builder()
            .baseUrl(BASE_URL + EVALUATION)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PutEvaluation::class.java)
    }
}