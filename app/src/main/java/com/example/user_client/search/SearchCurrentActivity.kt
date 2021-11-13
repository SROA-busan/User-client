package com.example.user_client.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.user_client.R
import com.example.user_client.databinding.SearchActivityCurrentBinding
import com.example.user_client.dto.CustomerResesrvationInfo
import com.example.user_client.dto.SearchData
import com.example.user_client.network.RetrofitInstance
import com.example.user_client.sign.SignInActivity
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchCurrentActivity : AppCompatActivity() {
    private val dataset = ArrayList<CustomerResesrvationInfo>()
    private lateinit var binding: SearchActivityCurrentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchActivityCurrentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //고객 예약정보 조회
        getCurrentRepairList()
    }

    //고객의 현재 예약정보 조회
    fun getCurrentRepairList() {

        val currentRepairList = RetrofitInstance().getData()
        currentRepairList.getCurrentRepairList(SignInActivity.userId)
            .enqueue(object: Callback<List<CustomerResesrvationInfo>>{
                override fun onResponse(call: Call<List<CustomerResesrvationInfo>>, response: Response<List<CustomerResesrvationInfo>>) {
                    //받은 리스트를 데이터 셋에 추가
                    if(response.body() != null)
                        response.body()!!.forEach {
                            dataset.add(it)
                        }
                    //TODO 값이 이상함
//                  [CustomerResesrvationInfo(scheduleNum=2, productName=냉장고, startDate=2021-11-04 14:01, endDate=-, content=냉장고, flag=0)
                    Log.d("현재 상태 : ", dataset.toString())
                    //리사이클러뷰 설정
                    setRecycler()
                }

                override fun onFailure(call: Call<List<CustomerResesrvationInfo>>, t: Throwable) {
                    Log.e("상태 : ", "통신실패")
                }
            })
    }

    //리사이클러뷰 셋팅
    private fun setRecycler() {
        val mRecyclerView = binding.searchRecyclerCurrent
        //넘겨줄 페이지 설정
        val intent = Intent(applicationContext, SearchDetailActivity::class.java)
        val adapter = SearchAdapter(dataset)
        //아이템 클릭 이벤트 설정
        adapter.setOnItemClickListener(object : SearchAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                //넘겨줄 데이터 설정
                intent.putExtra("scheduleNum", dataset.get(position).scheduleNum)
                startActivity(intent)
            }
        })

        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }
}
