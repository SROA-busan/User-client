package com.example.user_client.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.user_client.R
import com.example.user_client.databinding.SearchActivityCurrentBinding
import com.example.user_client.dto.SearchData

class SearchCurrent : AppCompatActivity() {
    companion object{
        val dataset = ArrayList<SearchData>()
    }
    private var binding: SearchActivityCurrentBinding? = null
    private val view get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchActivityCurrentBinding.inflate(layoutInflater)
        setContentView(view.root)

        val mRecyclerView = view.searchRecyclerCurrent
        val mSearchData = SearchData() // 디폴트
        dataset.add(mSearchData)
        mRecyclerView.adapter = SearchCurrentAdapter(dataset)
        mRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}