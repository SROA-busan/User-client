package com.example.user_client.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.user_client.R
import com.example.user_client.databinding.SearchActivityCurrentBinding
import com.example.user_client.dto.SearchData
import com.google.gson.Gson

class SearchCurrentActivity : AppCompatActivity() {
    companion object{
        val dataset = ArrayList<SearchData>()
    }
    private lateinit var binding: SearchActivityCurrentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchActivityCurrentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecycler()
    }

    private fun setRecycler(){
        val mRecyclerView = binding.searchRecyclerCurrent
        //TODO 서버에서 값 받아오기
        val mSearchData = SearchData(
            "2021-10-27",
            "바퀴벌레",
            "바퀴벌레가 바퀴타고 굴러다니고있어요",
            "진행중",
            R.color.진행중
        )
        dataset.add(mSearchData)

        val intent = Intent(applicationContext, SearchDetailActivity::class.java)
        val adapter = SearchCurrentAdapter(dataset)
        //아이템 클릭 이벤트 설정
        adapter.setOnItemClickListener(object: SearchCurrentAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                intent.putExtra("searchData", dataset.get(position))
                startActivity(intent)
            }
        })

        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }
}