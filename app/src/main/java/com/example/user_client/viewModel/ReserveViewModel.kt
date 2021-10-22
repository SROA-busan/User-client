package com.example.user_client.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.user_client.dto.ReserveData

class ReserveViewModel: ViewModel() {
    lateinit var reserveData: MutableLiveData<ReserveData>

    private val name: MutableLiveData<String> by lazy{ MutableLiveData<String>() }
    val address: String = "test"
    val emergencyCall: String = "0000"
    val product: String = ""
    val productInfo: String = "test"

    fun getName(name: String){

    }
}
