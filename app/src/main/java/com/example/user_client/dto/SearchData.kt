package com.example.user_client.dto

import com.example.user_client.R
import java.io.Serializable

data class SearchData(
    val dateTime: String = "0",
    val product: String = "0",
    val textArea: String = "000",
    val process: String = "0000?",
    val imageButtonColor: Int = R.color.fuckingblue
): Serializable
