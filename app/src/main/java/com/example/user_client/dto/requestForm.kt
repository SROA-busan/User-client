package com.example.user_client.dto

import android.net.Uri
import java.io.Serializable


data class requestForm(
    val id:String,
    val pw:String,
): Serializable
