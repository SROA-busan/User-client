package com.example.user_client.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.user_client.MainActivity
import com.example.user_client.databinding.SignInActivityBinding

class SignInActivity : AppCompatActivity(){
    private lateinit var binding: SignInActivityBinding
    private val view get() = binding!!
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInActivityBinding.inflate(layoutInflater)
        setContentView(view.root)
        /*
        서버 로그인 리턴
        성공 0
        존재하지 않는 아이디 1
        비밀번호 틀림 2
        엔지니어 최초 로그인 3
         */
        binding.loginButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.signUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.findInfoButton.setOnClickListener {
            startActivity(Intent(this, FindSignInfoActivity::class.java))
        }
    }
}