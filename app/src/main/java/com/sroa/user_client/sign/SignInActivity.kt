package com.sroa.user_client.sign

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sroa.user_client.MainActivity
import com.sroa.user_client.R
import com.sroa.user_client.databinding.SignInActivityBinding
import com.sroa.user_client.network.RetrofitInstance
import com.sroa.user_client.service.GetSignInService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class SignInActivity : AppCompatActivity(){
    companion object{
        var userId = ""
    }
    private lateinit var binding: SignInActivityBinding
    private val view get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInActivityBinding.inflate(layoutInflater)
        setContentView(view.root)

        //영문, 숫자만 입력
        binding.userId.filters = arrayOf(inputFilter())

        //로그인
        binding.signInButton.setOnClickListener {
            userId = binding.userId.text.toString()
            //            if(binding.userId.toString().contains("^[a-zA-Z]+\$")){
//                binding.signInIdWarning.text = "숫자만 입력은 불가"
//            }
            //입력값 검증
            if(checkSignIn("signInButton")){
                signIn(userId, binding.password.text.toString())
//                startActivity(Intent(this, MainActivity::class.java))
            }
        }
        //회원가입
        binding.signUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        //아이디/비밀번호 조회
        binding.findInfoButton.setOnClickListener {
            startActivity(Intent(this, SearchAddress::class.java))
        }
    }
    
    //로그인
    fun signIn(id: String, pw: String){
        //API경로 인터페이스로 레트로핏 인스턴스 생성
        val service = RetrofitInstance().getSignInInstance()
        Log.d("아이디 : ", id)
        //api호출
        service.login(id, pw).apply {
            //콜백
            enqueue(object: Callback<Int> {
                //서버 로그인 리턴
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    //성공 0
                    if (response.body() == 0) {
                        Toast.makeText(applicationContext, "로그인이 완료됬습니다.", Toast.LENGTH_SHORT).show();
                        startActivity(Intent(applicationContext, com.sroa.user_client.MainActivity::class.java))
                    }
                    //존재하지 않는 아이디 1
                    else if (response.body() == 1)
                        checkSignIn("canNotUse")
                    //비밀번호 틀림 2
                    else if (response.body() == 2)
                        checkSignIn("wrongPassword")
                    // 그 외
                    else
                        Log.d("상태 : ", "response.body() = " + response.body())
                }
                //실패시
                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.d("상태 : ","콜백 실패\n이유 : ${t}")
                    Toast.makeText(applicationContext, "실패", Toast.LENGTH_SHORT).show();
                }

            })
        }
    }
    //아이디 입력검사
    private fun checkSignIn(case: String): Boolean{
        //초기값
        binding.apply{
            signInIdWarning.text = ""
            signInIdWarning.setTextColor(resources.getColor(R.color.blue))
            signInPasswordWarning.text = ""
            signInPasswordWarning.setTextColor(resources.getColor(R.color.blue))
        }
        when(case){
            "signInButton" -> {
                if(binding.userId.text.isNullOrBlank()){
                    binding.signInIdWarning.apply {
                        text = "아이디를 입력해주세요"
                        return false
                    }
                }
                if(binding.password.text.isNullOrBlank()){
                    binding.signInPasswordWarning.apply {
                        text = "비밀번호를 입력해주세요"
                        return false
                    }
                }
            }
            "checkRedex" -> binding.signInIdWarning.apply {
                setTextColor(resources.getColor(R.color.red))
                text = "영문, 숫자, 특수문자를 입력해주세요"
            }
            "canNotUse" -> binding.signInIdWarning.apply {
                setTextColor(resources.getColor(R.color.red))
                text = "아이디를 찾을 수 없습니다."
            }
            "wrongPassword" -> binding.signInPasswordWarning.apply {
                setTextColor(resources.getColor(R.color.red))
                text = "비밀번호가 틀렸습니다."
            }
        }
        return true
    }
    // 영문만 허용
    fun inputFilter() = object : InputFilter {
        override fun filter(
            source: CharSequence?,
            start: Int,
            end: Int,
            dest: Spanned?,
            dstart: Int,
            dend: Int
        ): CharSequence? {
            /*
            영문만 허용
                "^[a-zA-Z]+\$"
            영문만 허용(숫자 포함)
                ^[a-zA-Z0-9]+$
             */
            val regex = "^[a-zA-Z0-9]+\$"
            val ps = Pattern.compile(regex)

            if (!ps.matcher(source).matches()) {
                checkSignIn("checkRedex")
                return ""
            }
            return null
        }
    }
}