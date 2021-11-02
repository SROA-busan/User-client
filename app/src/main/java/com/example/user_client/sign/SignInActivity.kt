package com.example.user_client.sign

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.user_client.MainActivity
import com.example.user_client.databinding.SignInActivityBinding
import com.example.user_client.network.RetrofitInstance
import com.example.user_client.service.GetSignInService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity(){
    companion object{
        var id = ""
    }
    private lateinit var binding: SignInActivityBinding
    private val view get() = binding!!
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInActivityBinding.inflate(layoutInflater)
        setContentView(view.root)

        //로그인
        binding.signInButton.setOnClickListener {
            signIn()
        }
        //회원가입
        binding.signUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        //아이디/비밀번호 조회
        binding.findInfoButton.setOnClickListener {
            startActivity(Intent(this, FindSignInfoActivity::class.java))
        }
    }
    
    //로그인
    fun signIn(){
        //API경로 인터페이스로 레트로핏 인스턴스 생성
        val service = RetrofitInstance().getSignInInstance()
        //api호출
        service.login(binding.username.text.toString(), binding.password.text.toString()).apply {
            //콜백
            enqueue(object: Callback<Int> {
                //서버 로그인 리턴
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    //성공 0
                    if (response.body() == 0) {
                        id = binding.username.text.toString()
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
    }
}