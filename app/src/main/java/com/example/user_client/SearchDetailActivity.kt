package com.example.user_client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.user_client.databinding.SearchActivityDetailBinding
import com.example.user_client.dto.SearchData

class SearchDetailActivity : AppCompatActivity() {
    private lateinit var binding: SearchActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        //TODO 직렬화되서 넘어온 데이터 출력하기
//        val intent = Intent().getSerializableExtra("reserveData")
        Log.d("제목 : intent데이터 테스트", intent.toString())
    }
}