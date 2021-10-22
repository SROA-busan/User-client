package com.example.user_client.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.user_client.databinding.SearchActivityDetailBinding
import com.example.user_client.dto.SearchData
import com.google.gson.Gson

class SearchDetailActivity : AppCompatActivity() {
    private lateinit var binding: SearchActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        //인텐트 호출
        val intent = getIntent()
        var searchData = intent.getSerializableExtra("reserveData")

        if(searchData == null){
            searchData = SearchData()
        }
    }
}