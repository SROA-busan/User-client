package com.example.user_client.dto

data class ReserveData(
    val userId: String,
    val customerName: String,
    val classifyName: String,
    val address: String,
    val dateTime: String,
    val phoneNum: String,
    val content: String
)
