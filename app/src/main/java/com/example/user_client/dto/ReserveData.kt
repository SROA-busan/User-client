package com.example.user_client.dto

data class ReserveData(
    val name: String = "test",
    val address: String = "test",
    val emergencyCall: String = "0000",
    val product: String,
    val productInfo: String = "test"
)
