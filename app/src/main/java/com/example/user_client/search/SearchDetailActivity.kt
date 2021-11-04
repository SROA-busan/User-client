package com.example.user_client.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.user_client.R
import com.example.user_client.databinding.SearchActivityDetailBinding
import com.example.user_client.dto.CustomerReservationDetailInfo
import com.example.user_client.dto.SearchData
import com.example.user_client.dto.UserInfo
import com.example.user_client.network.RetrofitInstance
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchDetailActivity : AppCompatActivity() {
    //TODO intent 넘어온 값을 데이터바인딩해주기
    private lateinit var binding: SearchActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDetailReserveInfo()
        //인텐트 호출
        val intent = getIntent()
        var searchData = intent.getSerializableExtra("searchData") as SearchData
        var userInfo = UserInfo(
            "hwan4789",
            "1q2w3e4r",
            "박상환",
            "부산광역시 남구 유엔평화로 29번길 99",
            "01040275019",
            111,
        )
        binding.searchData = searchData
        binding.userInfo = userInfo
    }
    //고객 예약 상세정보 조회
    //TODO 질문 : Detail조회할 때 EndDate가 없으면 조회가 안되게 되어있는데 그렇게 되면 예약한 직후 자신의 예약현황을 못보는게 아닌가??
    fun getDetailReserveInfo(){
        val getDetailInfo = RetrofitInstance().getData()
        getDetailInfo.getDetailRepairInfo(4L).enqueue(object: Callback<CustomerReservationDetailInfo>{
            override fun onResponse(
                call: Call<CustomerReservationDetailInfo>,
                response: Response<CustomerReservationDetailInfo>
            ) {
                Log.d("상태 : ", response.body().toString())
            }

            override fun onFailure(call: Call<CustomerReservationDetailInfo>, t: Throwable) {
                Log.d("상태 : ", "통신실패")
            }
        })
    }
}