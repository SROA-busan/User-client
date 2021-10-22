package com.example.user_client.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
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
        val mSearchData = SearchData() // 디폴트

        dataset.add(mSearchData)

        val intent = Intent(applicationContext, SearchDetailActivity::class.java)
        val adapter = SearchCurrentAdapter(dataset)

        adapter.setOnItemClickListener(object: SearchCurrentAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                intent.putExtra("reserveData", dataset.get(position))
                startActivity(intent)
            }
        })

        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }
}