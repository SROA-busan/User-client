package com.example.user_client.sign

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.Spanned
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.user_client.MapActivity
import com.example.user_client.databinding.SignUpActivityBinding
import com.example.user_client.dto.UserInfo
import com.example.user_client.network.RetrofitInstance
import com.example.user_client.service.GetSignInService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    private var binding: SignUpActivityBinding? = null
    private val view get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpActivityBinding.inflate(layoutInflater)
        setContentView(view.root)

        view.editUserId.filters = arrayOf(inputFilter())
        //회원가입 버튼
        confirmButtonEvent()
        view.editUserAddress.setOnClickListener {
//            openMap()
        }
    }

    fun openMap() {
//        val service = RetrofitInstance().getMapInstance()
//        service.getJuso(GetJusoService.KEY, Context.FILE_INTEGRITY_SERVICE, "1")
    }

    //회원가입 버튼
    fun confirmButtonEvent() {
        view.signUpConfirmButton.setOnClickListener {
            userSignUp()
        }
    }

    //회원가입 서버 통신
    fun userSignUp() {
        val service = RetrofitInstance().getSignInInstance()
        val user = getUserInfo()

        if (checkUserInfo(user)) {
            service.userSignup(user).enqueue(object : Callback<Boolean> {
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    //회원가입
                    if (response.body().toString().toBoolean()) {
                        Toast.makeText(applicationContext, "회원가입 성공", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext, SignInActivity::class.java))
                    } else
                        Toast.makeText(applicationContext, "중복된 정보가 있습니다", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.d("콜백 : ", "통신 실패")
                }
            })
        }
    }

    //유저정보 반환
    fun getUserInfo(): UserInfo = UserInfo(
        binding!!.editUserId.text.toString(),
        binding!!.editUserPw.text.toString(),
        binding!!.editUserName.text.toString(),
        binding!!.editUserAddress.text.toString(),
        binding!!.editUserPhone.text.toString(),
        1
    )

    //유효성 검증
    fun checkUserInfo(userInfo: UserInfo): Boolean {
        //TODO 아이디 중복검사
        if (userInfo.id.isEmpty()) {
            Toast.makeText(applicationContext, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
            return false
        } else if (userInfo.pw.isEmpty()) {
            Toast.makeText(applicationContext, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            return false
        } else if (userInfo.name.isEmpty()) {
            Toast.makeText(applicationContext, "이름을 입력해주세요", Toast.LENGTH_SHORT).show()
            return false
        } else if (userInfo.address.isEmpty()) {
            Toast.makeText(applicationContext, "주소를 입력해주세요", Toast.LENGTH_SHORT).show()
            return false
        } else if (userInfo.phoneNum.isEmpty()) {
            Toast.makeText(applicationContext, "전화번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            return false
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
            val regex = "^[a-zA-Z]+\$"
            val ps = Pattern.compile(regex)

            if (!ps.matcher(source).matches()) {
                Toast.makeText(applicationContext, "영어만 입력해주세요", Toast.LENGTH_SHORT).show()
                return ""
            }
            return null
        }
    }
}
