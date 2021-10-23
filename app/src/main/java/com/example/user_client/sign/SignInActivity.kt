package com.example.user_client.sign

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.user_client.MainActivity
import com.example.user_client.Retrofit.RetrofitBuilder
import com.example.user_client.databinding.SignInActivityBinding
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
        /*
        서버 로그인 리턴
        성공 0
        존재하지 않는 아이디 1
        비밀번호 틀림 2
        엔지니어 최초 로그인 3
         */
        binding.loginButton.setOnClickListener {
            val callGetLogin = RetrofitBuilder.api.login(binding.username.text.toString(), binding.password.text.toString());
            Log.d("로그 테스트", "ㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅ")
            print(binding.username.text.toString());

            callGetLogin.enqueue(object: Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
//            Log.d("로그 테스트", "22ㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅㅅ")
                    if (response.isSuccessful()) {
                        if (response.body() == 0)
                            Toast.makeText(applicationContext, "로그인이 완료됬습니다.", Toast.LENGTH_SHORT).show();
                        else if (response.body() == 1)
                            Toast.makeText(applicationContext, "존재하지 않는 아이디.", Toast.LENGTH_SHORT).show();
                        else if (response.body() == 2)
                            Toast.makeText(applicationContext, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();

                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Toast.makeText(applicationContext, "싷패.", Toast.LENGTH_SHORT).show();
                }
            })
//            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.signUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.findInfoButton.setOnClickListener {
            startActivity(Intent(this, FindSignInfoActivity::class.java))
        }
    }
}