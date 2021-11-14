package com.example.user_client.search

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.user_client.MainActivity
import com.example.user_client.R
import com.example.user_client.databinding.SearchActivityDetailBinding
import com.example.user_client.dto.CustomerReservationDetailInfo
import com.example.user_client.dto.SearchData
import com.example.user_client.evalution.EvaluationActivity
import com.example.user_client.network.RetrofitInstance
import com.example.user_client.reserve.ReserveInputFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class SearchDetailActivity : AppCompatActivity() {
    private lateinit var binding: SearchActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = getIntent()
        //예약번호 호출
        val scheduleNum = intent.getLongExtra("scheduleNum", -1)
        Log.d("조회 상세 scheduleNum", scheduleNum.toString())
        //예약상세 조회
        if (scheduleNum != -1L)
            getDetailReserveInfo(scheduleNum)

        //평가 입력
        setEvaluationButtons(scheduleNum)
        //반납예약
        setRepairButton(scheduleNum)
        binding.lifecycleOwner = this
    }

    //고객 예약 상세정보 조회
    //EndDate가 없으면 조회 안됨
    fun getDetailReserveInfo(scheduleNum: Long) {

        val getDetailInfo = RetrofitInstance().getData()
        getDetailInfo.getDetailRepairInfo(scheduleNum).enqueue(object : Callback<CustomerReservationDetailInfo> {
            override fun onResponse(
                call: Call<CustomerReservationDetailInfo>,
                response: Response<CustomerReservationDetailInfo>
            ) {
                if (response.body() != null) {
                    binding.detailInfo = response.body()
                    response.body()!!.apply {
                        binding.searchDetailInquiryDate.text = startDate + " ~ " + endDate
                    }

                    //전화버튼 설정
                    setCallButton(response.body()!!.engineerPhoneNum)
                }
            }

            override fun onFailure(call: Call<CustomerReservationDetailInfo>, t: Throwable) {
                Log.e("상태 : ", "통신실패")
            }
        })
    }

    //전화버튼 설정
    private fun setCallButton(phoneNumber: String) {
        binding.searchDetailButtonCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null))
            startActivity(intent)
        }
    }

    //평가입력 버튼
    private fun setEvaluationButtons(scheduleNum: Long){
        binding.searchDetailEvaluationButton.setOnClickListener {
            intent = Intent(applicationContext, EvaluationActivity::class.java)
            intent.putExtra("scheduleNum", scheduleNum)
            startActivity(intent)
        }
    }

    //반납예약 버튼
    private fun setRepairButton(scheduleNum: Long){
        binding.searchDetailRepairButton.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra("from", 1)
            intent.putExtra("scheduleNum", scheduleNum)
            startActivity(intent)
        }
    }
}