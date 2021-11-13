package com.example.user_client.dto

data class WriteEvaluation(
    val content: String,
    val score: Int,
    val scheduleNum: Long
)