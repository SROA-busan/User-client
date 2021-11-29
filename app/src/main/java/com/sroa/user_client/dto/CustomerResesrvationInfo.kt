package com.sroa.user_client.dto

import java.io.Serializable

data class CustomerResesrvationInfo(
    val scheduleNum: Long,  //일정 번호
    val productName: String,//제품 정보
    val startDate: String,  //시작 일시
    val endDate: String,    //종료 일시
    val content: String,    //내용
    val flag: Int           //상태
)