package com.example.user_client.sign

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.user_client.MainActivity
import com.example.user_client.databinding.SignInActivityBinding
import com.example.user_client.network.RetrofitInstance
import com.example.user_client.service.GetService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity(){
    private lateinit var binding: SignInActivityBinding
    private val view get() = binding!!
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInActivityBinding.inflate(layoutInflater)
        setContentView(view.root)
        
        binding.loginButton.setOnClickListener {
            Log.d("상태 : ", "클릭 됨")
            //API경로 인터페이스로 레트로핏 인스턴스 생성
            val service = RetrofitInstance().getRetrofitInstance().create(GetService::class.java)
            //api호출
            val call = service.login(binding.username.text.toString(), binding.password.text.toString())
            //콜백
            call.enqueue(object: Callback<Int> {    
                //서버 로그인 리턴
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    //성공 0
                    if (response.body() == 0) {
                        Toast.makeText(applicationContext, "로그인이 완료됬습니다.", Toast.LENGTH_SHORT).show();
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                    }
                    //존재하지 않는 아이디 1
                    else if (response.body() == 1)
                        Toast.makeText(applicationContext, "존재하지 않는 아이디.", Toast.LENGTH_SHORT).show();
                    //비밀번호 틀림 2
                    else if (response.body() == 2)
                        Toast.makeText(applicationContext, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                    // 그 외
                    else
                        Log.d("상태 : ", "response.body() = " + response.body())
                }
                //실패시
                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.d("상태 : ","콜백 실패")
                    Toast.makeText(applicationContext, "실패", Toast.LENGTH_SHORT).show();
                }

            })
        }

        binding.signUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.findInfoButton.setOnClickListener {
            startActivity(Intent(this, FindSignInfoActivity::class.java))
        }
    }
}