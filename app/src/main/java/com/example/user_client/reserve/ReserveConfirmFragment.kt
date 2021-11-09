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
        binding.reserveConfirmButton.setOnClickListener {
            val mMainActivity = activity as MainActivity
            //예약 푸싱
            pushReserveData()

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
        val pushReserveSchdule = RetrofitInstance().getReservationSchedule()
        //예약정보 전송
        pushReserveSchdule.pushReserveData(reserveData).enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                Log.d("내용 : ", response.body()!!.toString())
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.e("내용 : ", "통신실패 ${t}")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}