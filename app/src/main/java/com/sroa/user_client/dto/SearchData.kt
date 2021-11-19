package com.sroa.user_client.dto

import com.sroa.user_client.R
import java.io.Serializable

data class SearchData(
    val dateTime: String,
    val product: String,
    val productInfo: String,
    val process: String,
    val imageButtonColor: Int
) : Serializable
