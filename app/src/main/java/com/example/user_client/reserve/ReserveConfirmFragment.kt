package com.example.user_client.reserve

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.user_client.MainActivity
import com.example.user_client.databinding.ReserveFragmentConfirmBinding
import com.example.user_client.dto.EngineerInfo
import com.example.user_client.dto.ReserveData
import com.example.user_client.network.RetrofitInstance
import com.example.user_client.sign.SignInActivity
import com.example.user_client.viewModel.ReserveViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.util.*

class ReserveConfirmFragment: Fragment() {
    private var _binding: ReserveFragmentConfirmBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ReserveViewModel

    //inflate
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ReserveFragmentConfirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ReserveViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        //뒤로가기 이벤트
        setBackPressed()
        //버튼 이벤트
        setButtonEvent()
    }

    //뒤로가기 이벤트
    private fun setBackPressed(){
        val activity = activity as MainActivity
        activity.setHomeEnabled(true)
        activity.fragment = ReserveSelectFragment()
    }
    //버튼 이벤트
    private fun setButtonEvent(){
        binding.reserveConfirmButton.setOnClickListener {
            if(viewModel.reReservation.value!!){
                //재예약 푸싱
                pushReReserveDate(viewModel.confirmDateTime.value!!, viewModel.scheduleNum.value!!)
            }
            else{
                //예약 푸싱
                pushReserveData()
            }

            val mMainActivity = activity as MainActivity
            mMainActivity.changeReserveFragment("detail")
        }
    }

    //예약푸싱
    private fun pushReserveData() {
        //푸시할 데이터 설정
        val reserveData = ReserveData(
            SignInActivity.userId,          //아이디
            viewModel.customerName.value!!, //고객 이름
            viewModel.classifyName.value!!, //분류이름
            viewModel.address.value!!,      //주소
            viewModel.confirmDateTime.value!!, //입력받은 날짜
            viewModel.phoneNumber.value!!,  //폰번호
            viewModel.content.value!!       //상세내용
        )
        //일정 예약 인스턴스 호출
        val pushReserveService = RetrofitInstance().getReservationSchedule()
        //예약정보 전송
        pushReserveService.pushReserveData(reserveData).enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                Log.d("예약 푸싱 성공", response.body().toString())
                viewModel.engineerName.value = response.body()!!.get(0)         //엔지니어 이름
                viewModel.serviceCenterName.value = response.body()!!.get(1)    //서비스 센터 이름
                viewModel.engineerPhoneNumber.value = response.body()!!.get(2)  //엔지니어 전화번호
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.e("내용 : ", "통신실패 ${t}")
            }
        })
    }
    
    //재예약 푸싱
    private fun pushReReserveDate(date: String, scheduleNum: Long){
        val pushReReservationService = RetrofitInstance().getReservationSchedule()
        pushReReservationService.pushReReserveDate(scheduleNum, date).enqueue(object: Callback<Boolean>{
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                Log.d("재 예약 푸싱 성공", response.body().toString())
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.e("통신 실패", t.toString())
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}