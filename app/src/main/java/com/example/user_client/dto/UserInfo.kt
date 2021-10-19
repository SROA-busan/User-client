package com.example.user_client.dto

data class UserInfo(

    private var userNum: Long? = null,
    private val id: String,
    private val pw: String,
    private val name: String,
    private val address: String,
    private val phoneNum: String,
    private val code: Int? = null
) {
}