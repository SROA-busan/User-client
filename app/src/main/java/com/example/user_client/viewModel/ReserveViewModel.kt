package com.example.user_client.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.user_client.dto.ReserveData

class ReserveViewModel: ViewModel() {

    private var name = MutableLiveData<String>("test")
    private val emergencyCall = MutableLiveData<String>()
    private val product = MutableLiveData<String>()
    private val productInfo = MutableLiveData<String>()
    private val address = MutableLiveData<String>()

    fun getName(): LiveData<String> = name
    fun getEmergency(): LiveData<String> = emergencyCall
    fun getProduct(): LiveData<String> = product
    fun getProductInfo(): LiveData<String> = productInfo
    fun getAddress(): LiveData<String> = address

    fun setName(text: String){
        this.name = MutableLiveData<String>(text)
    }
}
