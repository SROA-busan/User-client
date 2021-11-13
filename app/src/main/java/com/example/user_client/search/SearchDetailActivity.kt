package com.example.user_client.search

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.user_client.databinding.SearchActivityDetailBinding
import com.example.user_client.dto.CustomerReservationDetailInfo
import com.example.user_client.evalution.EvaluationActivity
import com.example.user_client.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchDetailActivity : AppCompatActivity() {
    private lateinit var binding: SearchActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = getIntent()
        //예약번호 호출
        val searchData = intent.getLongExtra("scheduleNum", -1)

        //예약상세 조회
        if (searchData != -1L)
            getDetailReserveInfo(searchData)
        binding.lifecycleOwner = this
        
        //평가입력 버튼
        binding.searchDetailEvaluation.setOnClickListener { 
            intent = Intent(applicationContext, EvaluationActivity::class.java)
            intent.putExtra("scheduleNum", searchData)
            startActivity(intent)
        }
    }

    //고객 예약 상세정보 조회
    //TODO EndDate가 없으면 조회 안됨
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
}