package com.example.user_client.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.user_client.R
import com.example.user_client.databinding.SearchActivityPreviousBinding
import com.example.user_client.dto.SearchData

class SearchPreviousActivity : AppCompatActivity() {
    companion object{
        val dataset = ArrayList<SearchData>()
    }
    private var binding : SearchActivityPreviousBinding? = null
    private val view get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchActivityPreviousBinding.inflate(layoutInflater)
        setContentView(view.root)

        val mRecyclerView = view.searchRecyclerPrevious
        val mSearchData = SearchData() // 디폴트
        dataset.add(mSearchData)
        mRecyclerView.adapter = SearchPreviousAdapter(dataset)
        mRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}