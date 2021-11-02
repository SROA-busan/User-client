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
    //로그인
    @GET("/account/login/{ID}/{PW}")
    fun login(@Path("ID") id: String, @Path("PW") pw: String): Call<Int>

    //회원가입
    @POST("/account/customer/singup")
    fun userSignup(@Body userInfo: UserInfo): Call<Boolean>
}

interface GetDataService {
    //id로 유저정보 로드
    @GET("")
    fun getUserInfo(@Path("ID") id: String): Call<UserInfo>

    @GET("/schedule/findAvailableTime/{date}/{address}")
    fun findAvailableTime(@Path("date") date: String, @Path("address") address: String): Call<List<Boolean>>
}
//interface GetJusoService {
//    companion object{
//        val BASE_URL = "https://www.juso.go.kr"
//        val KEY = "devU01TX0FVVEgyMDIxMTEwMTE3NTAwNDExMTgyODM="
//    }
//    @GET("/addrlink/addrMobileLinkUrl.do")
//    public fun getJuso(confmKey: String, returnUrl: String, resultType: String)
//}