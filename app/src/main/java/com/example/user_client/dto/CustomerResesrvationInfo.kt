package com.example.user_client.dto

data class CustomerResesrvationInfo(
    val scheduleNum: Long,
    val productName: String,
    val startDate: String,
    val endDate: String,
    val content: String,
//true : 수리, false : 반납
    val flag: Int
)