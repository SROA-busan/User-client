package com.example.user_client.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.user_client.R
import com.example.user_client.databinding.SearchActivityPreviousBinding
import com.example.user_client.dto.CustomerResesrvationInfo
import com.example.user_client.dto.SearchData
import com.example.user_client.network.RetrofitInstance
import com.example.user_client.sign.SignInActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPreviousActivity : AppCompatActivity() {
    private val dataset = ArrayList<CustomerResesrvationInfo>()

    private lateinit var binding: SearchActivityPreviousBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchActivityPreviousBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //이전 내역조회
        getPreviousRepairList()
    }

    //지난 예약 현황조회 flag = 1, 5
    fun getPreviousRepairList() {
        val previousRepairList = RetrofitInstance().getData()
        previousRepairList.getLastRepairList(SignInActivity.userId)
            .enqueue(object : Callback<List<CustomerResesrvationInfo>> {
                override fun onResponse(
                    call: Call<List<CustomerResesrvationInfo>>,
                    response: Response<List<CustomerResesrvationInfo>>
                ) {
                    Log.d("지난 예약현황 조회 ", response.body().toString())
                    if (response.body() != null) {
                        response.body()!!.forEach {
                            dataset.add(it)
                        }
                    }
                    //데이터 셋팅
                    //리사이클러뷰 호출
                    setRecycler()
                }

                override fun onFailure(call: Call<List<CustomerResesrvationInfo>>, t: Throwable) {
                    Log.e("통신실패 : ", "${t}")
                }
            })
    }

    //리사이클러뷰 셋팅
    private fun setRecycler() {
        val mRecyclerView = binding.searchRecyclerPrevious
        //넘겨줄 페이지
        val intent = Intent(applicationContext, SearchDetailActivity::class.java)
        val adapter = SearchAdapter(dataset)
        //아이템 클릭 이벤트 설정
        adapter.setOnItemClickListener(object : SearchAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                //넘겨줄 데이터
                intent.putExtra("scheduleNum", dataset.get(position).scheduleNum)
                startActivity(intent)
            }
        })

        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}