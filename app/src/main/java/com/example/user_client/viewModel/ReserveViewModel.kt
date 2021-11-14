package com.example.user_client.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReserveViewModel : ViewModel() {
    val customerName = MutableLiveData<String>() //고객 명
    val phoneNumber = MutableLiveData<String>()  //고객 전화번호
    val classifyName = MutableLiveData<String>() //제품번호
    val content = MutableLiveData<String>()      //상세내용
    val address = MutableLiveData<String>()      //주소
    val confirmDateTime = MutableLiveData<String>()//예약 확인한 날짜
    val serviceCenterName = MutableLiveData<String>() //서비스 센터이름
    val engineerName = MutableLiveData<String>() //엔지니어 이름
    val engineerPhoneNumber = MutableLiveData<String>() //엔지니어 전화번호

    val reReservation = MutableLiveData<Boolean>(false)//false 일경우 새 예약, true 일경우 재 예약
    val scheduleNum = MutableLiveData<Long>()
}
