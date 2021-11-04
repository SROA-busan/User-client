package com.example.user_client.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReserveViewModel : ViewModel() {
    val customerName = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()
    val classifyName = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val address = MutableLiveData<String>()
}
