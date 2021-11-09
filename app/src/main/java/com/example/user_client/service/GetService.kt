package com.example.user_client.service

import com.example.user_client.dto.CustomerReservationDetailInfo
import com.example.user_client.dto.CustomerResesrvationInfo
import com.example.user_client.dto.ReserveData
import com.example.user_client.dto.UserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.*

//api경로를 기술하는 인터페이스
//유저 로그인, 회원가입관련 api
interface GetSignInService {
    //로그인
    @GET("/account/login/{ID}/{PW}")
    fun login(@Path("ID") id: String, @Path("PW") pw: String): Call<Int>
    //회원가입
    @POST("/account/customer/singup")
    fun userSignup(@Body userInfo: UserInfo): Call<Boolean>
    //아이디 중복검사
    @GET("account/duplicateCheck/{ID}")
    fun checkDuplicate(@Path("ID") id: String): Call<Boolean>
}

//유저정보 관련 api
interface GetDataService {
    //id로 유저정보 로드
    @GET("/schedule/customer/requestReservationPage/{ID}")
    fun getUserInfo(@Path("ID") id: String): Call<UserInfo>

    // 고객 어플 수리현황 클릭 -> 현재 처리 완료되지않은 일정 간략히 출력
    @GET("/schedule/customer/requestCurrentRepair/{id}")
    fun getCurrentRepairList(@Path("id") id: String): Call<List<CustomerResesrvationInfo>>

    // 고객 어플 지난수리현황 클릭 -> 처리 완료된 일정 간략히 출력z
    @GET("/schedule/customer/requestLastRepair/{id}")
    fun getLastRepairList(@Path("id") id: String): Call<List<CustomerResesrvationInfo>>

    //고객 하나의 일정에 대한 상세정보
    @GET("/schedule/customer/requestDetailSchedule/{scheduleNum}")
    fun getDetailRepairInfo(@Path("scheduleNum") scheduleNum: Long): Call<CustomerReservationDetailInfo>
}

//예약정보 관련 api
interface GetReservationSchedule{
    //가능한 시간 조회
    @GET("/schedule/findAvailableTime/{date}/{address}")
    fun findAvailableTime(@Path("date") date: String, @Path("address") address: String): Call<List<Boolean>>

    //예약정보 전송
    @POST("/schedule/allocateEngineer")
    fun pushReserveData(@Body reserveData: ReserveData): Call<List<String>>
}