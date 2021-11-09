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
    companion object{
        val dataset = ArrayList<SearchData>()
    }
    private lateinit var binding : SearchActivityPreviousBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchActivityPreviousBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPreviousRepairList()
        setRecycler()
    }
    //지난 예약 현황조회 flag = 1, 5
    fun getPreviousRepairList(){
        val previousRepairList = RetrofitInstance().getData()
        previousRepairList.getLastRepairList(SignInActivity.userId).enqueue(object : Callback<List<CustomerResesrvationInfo>>{
            override fun onResponse(
                call: Call<List<CustomerResesrvationInfo>>,
                response: Response<List<CustomerResesrvationInfo>>
            ) {
                Log.d("이전 상태 : ", response.body().toString())
            }

            override fun onFailure(call: Call<List<CustomerResesrvationInfo>>, t: Throwable) {
                Log.d("통신실패 : ", "${t}")
            }
        })
    }

    //리사이클러뷰 셋팅
    private fun setRecycler(){
        val mRecyclerView = binding.searchRecyclerPrevious
        val mSearchData = SearchData(
            "2021-10-27",
            "바퀴벌레",
            "바퀴벌레가 바퀴타고 굴러다니고있어요",
            "입고완료",
            R.color.진행중
        )
        dataset.add(mSearchData)

        val intent = Intent(applicationContext, SearchDetailActivity::class.java)
        val adapter = SearchAdapter(dataset)
        //아이템 클릭 이벤트 설정
        adapter.setOnItemClickListener(object: SearchAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                intent.putExtra("searchData", dataset.get(position))
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