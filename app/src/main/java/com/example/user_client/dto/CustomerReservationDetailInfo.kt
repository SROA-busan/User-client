package com.example.user_client.dto

data class CustomerReservationDetailInfo(
    val scheduleNum: Long,
    val productName: String,
    val content: String,
    val startDate: String,
    val endDate: String,
    val customerAddress: String,
    val centerName: String,
    val engineerName: String,
    val engineerPhoneNum: String,
    val status: Int
)

