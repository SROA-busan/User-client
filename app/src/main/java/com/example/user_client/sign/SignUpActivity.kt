package com.example.user_client.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.user_client.databinding.SignUpActivityBinding

class SignUpActivity : AppCompatActivity() {
    private var binding: SignUpActivityBinding? = null
    private val view get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpActivityBinding.inflate(layoutInflater)
        setContentView(view.root)

        view.signUpConfirmButton.setOnClickListener {
            Toast.makeText(applicationContext, "회원가입이 완료됬습니다.", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}