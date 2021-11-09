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
    companion object {
        val dataset = ArrayList<SearchData>()
    }

    private lateinit var binding: SearchActivityCurrentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchActivityCurrentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCurrentRepairList()
        setRecycler()
    }

    //고객의 현재 예약정보 조회 flag = 2, 3, 4
    fun getCurrentRepairList() {
        val currentRepairList = RetrofitInstance().getData()

        currentRepairList.getCurrentRepairList(SignInActivity.userId)
            .enqueue(object: Callback<List<CustomerResesrvationInfo>>{
                override fun onResponse(call: Call<List<CustomerResesrvationInfo>>, response: Response<List<CustomerResesrvationInfo>>) {
                    Log.d("현재 상태 : ", response.body().toString())
                }

                override fun onFailure(call: Call<List<CustomerResesrvationInfo>>, t: Throwable) {
                    Log.d("상태 : ", "통신실패")
                }
            })
    }

    //리사이클러뷰 셋팅
    private fun setRecycler() {
        val mRecyclerView = binding.searchRecyclerCurrent
        //TODO 서버에서 기 예약된 정보 받아오기
        val mSearchData = SearchData(
            "2021-10-27",
            "바퀴벌레",
            "바퀴벌레가 바퀴타고 굴러다니고있어요",
            "진행중",
            R.color.진행중
        )
        dataset.add(mSearchData)

        val intent = Intent(applicationContext, SearchDetailActivity::class.java)
        val adapter = SearchAdapter(dataset)
        //아이템 클릭 이벤트 설정
        adapter.setOnItemClickListener(object : SearchAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                intent.putExtra("searchData", dataset.get(position))
                startActivity(intent)
            }
        })

        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }
}
