package com.example.user_client.service

import com.example.user_client.dto.UserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

//api경로를 기술하는 인터페이스
//요청하는 서비스로 인터페이스명 변경
interface GetSignInService {
    companion object{
        //기본 URL설정
        val BASE_URL: String = "http://192.168.0.4:8000"
    }
    @GET("/account/login/{ID}/{PW}")
    fun login(@Path("ID") id:String, @Path("PW") pw:String): Call<Int>

    @POST("/account/customer/singup")
    fun userSignup(@Body userInfo: UserInfo): Call<Boolean>
}