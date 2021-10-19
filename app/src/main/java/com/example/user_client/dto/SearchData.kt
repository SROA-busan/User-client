package com.example.user_client.dto

import android.widget.ImageButton
import com.example.user_client.R
import java.util.*

data class SearchData(
    val dateTime : String = "0",
    val product : String = "펭귄",
    val textArea : String = "테스트",
    val process :String = "진행중?",
    val imageButtonColor : Int = R.color.fuckingblue
)
