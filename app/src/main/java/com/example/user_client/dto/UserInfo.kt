package com.example.user_client.dto

data class UserInfo(
    var id: String,
    var pw: String,
    var name: String,
    var address: String,
    var phoneNum: String,
    var code: Int? = null
) {
}