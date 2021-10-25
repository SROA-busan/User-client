package com.example.user_client.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.user_client.dto.ReserveData

class ReserveViewModel : ViewModel() {

    val name = MutableLiveData<String>()
    val emergencyCall = MutableLiveData<String>()
    val product = MutableLiveData<String>()
    val productInfo = MutableLiveData<String>()
    val address = MutableLiveData<String>()
}
