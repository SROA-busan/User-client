package com.example.user_client.dto

import com.example.user_client.R
import java.io.Serializable

data class SearchData(
    val dateTime: String,
    val product: String,
    val productInfo: String,
    val process: String,
    val imageButtonColor: Int
) : Serializable
