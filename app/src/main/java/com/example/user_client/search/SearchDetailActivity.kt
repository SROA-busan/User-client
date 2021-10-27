package com.example.user_client.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.user_client.R
import com.example.user_client.databinding.SearchActivityDetailBinding
import com.example.user_client.dto.SearchData
import com.example.user_client.dto.UserInfo
import com.google.gson.Gson

class SearchDetailActivity : AppCompatActivity() {
    //TODO intent 넘어온 값을 데이터바인딩해주기
    private lateinit var binding: SearchActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
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
}