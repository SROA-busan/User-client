package com.example.user_client.reserve

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.user_client.MainActivity
import com.example.user_client.databinding.ReserveFragmentDetailBinding
import com.example.user_client.dto.ReserveData
import com.example.user_client.network.RetrofitInstance
import com.example.user_client.sign.SignInActivity
import com.example.user_client.viewModel.ReserveViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class ReserveDetailFragment : Fragment(){
    private var _binding : ReserveFragmentDetailBinding? = null
    private lateinit var viewModel : ReserveViewModel
    private val binding get() = _binding!!

    //inflate
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ReserveFragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    //구현
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //뷰모델, 데이터 바인딩
        viewModel = ViewModelProvider(requireActivity()).get(ReserveViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        //예약정보 전송
        pushReserveData()
        //버튼 이벤트
        setButtonEvent()
    }
    
    //예약푸싱
    fun pushReserveData(){
        val reserveData = ReserveData(
            SignInActivity.userId,          //아이디
            viewModel.customerName.value!!, //고객 이름
            viewModel.classifyName.value!!, //분류이름
            viewModel.address.value!!,      //주소
            LocalDateTime.now().toString().substring(0, 16).replace('T', ' '),     //예약날짜(버튼 누른날짜)
            viewModel.phoneNumber.value!!,  //폰번호
            viewModel.content.value!!       //상세내용
        )
        val pushReserveSchdule = RetrofitInstance().getReservationSchedule()
        //예약정보 전송
        pushReserveSchdule.pushReserveData(reserveData).enqueue(object: Callback<List<Any>>{
            override fun onResponse(call: Call<List<Any>>, response: Response<List<Any>>) {
                //TODO 엔지니어 인포를 가져와야함
                Log.d("결과 : ", response.body().toString())
            }

            override fun onFailure(call: Call<List<Any>>, t: Throwable) {
                Log.d("결과 : ", "응답 실패")
            }
        })
    }

    //버튼클릭 이벤트
    fun setButtonEvent(){
        //전화버튼
        binding.confirmButtonCall.setOnClickListener {
            //버튼 클릭시 전화번호 띄워주기기
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "01040275019", null))
            startActivity(intent)
        }
        //이전 버튼
        binding.confirmButtonPrevious.setOnClickListener{
            val mMainActivity = activity as MainActivity
            mMainActivity.changeReserveFragment("select")
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}