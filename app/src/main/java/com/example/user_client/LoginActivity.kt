package com.example.user_client

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.user_client.databinding.LoginActivityBinding

class LoginActivity : AppCompatActivity(){
    private lateinit var binding: LoginActivityBinding
    private val view get() = binding!!
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(view.root)
        
        binding.loginButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}