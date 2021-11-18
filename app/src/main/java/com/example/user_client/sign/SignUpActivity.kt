package com.example.user_client.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.util.Log
import android.widget.Toast
import com.example.user_client.databinding.SignUpActivityBinding
import com.example.user_client.dto.UserInfo
import com.example.user_client.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    private var _binding: SignUpActivityBinding? = null
    private val binding get() = _binding!!
    private var isChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = SignUpActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editUserId.filters = arrayOf(inputFilter())
        //중복검사 버튼
        binding.duplicateButton.setOnClickListener {
            confirmDuplicate(binding.editUserId.text.toString())
        }
        //주소검색 버튼
        binding.editUserAddress.setOnClickListener {
//            openMap()
        }
        //회원가입 버튼
        binding.signUpConfirmButton.setOnClickListener {
            if(isChecked)
                userSignUp()
            else
                Toast.makeText(applicationContext, "중복검사 해주세요", Toast.LENGTH_SHORT).show()
        }
    }

    fun openMap() {
//        val service = RetrofitInstance().getMapInstance()
//        service.getJuso(GetJusoService.KEY, Context.FILE_INTEGRITY_SERVICE, "1")
    }

    //유저아이디 중복검사
    fun confirmDuplicate(checkId: String) {
        val checkDuplicate = RetrofitInstance().getSignInInstance()
        checkDuplicate.checkDuplicate(checkId).apply {
            enqueue(object: Callback<Boolean>{
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    if (response.body()!!){
                        Toast.makeText(applicationContext, "사용가능한 아이디입니다.", Toast.LENGTH_SHORT).show()
                        isChecked = true
                    }

                    else{
                        Toast.makeText(applicationContext, "이미 존재하는 아이디 입니다.", Toast.LENGTH_SHORT).show()
                        isChecked = false
                    }
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.d("결과 : ", "콜백 실패\n이유 : ${t}")
                }
            })
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
                    Log.d("콜백 : ", "통신 실패\n이유 : "+t)
                }
            })
        }
    }

    //유저정보 반환
    fun getUserInfo(): UserInfo = UserInfo(
        binding.editUserId.text.toString(),
        binding.editUserPw.text.toString(),
        binding.editUserName.text.toString(),
        binding.editUserAddress.text.toString(),
        binding.editUserPhone.text.toString(),
        1
    )

    //유효성 검증
    fun checkUserInfo(userInfo: UserInfo): Boolean {
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
            val regex = "^[a-zA-Z0-9]+\$"
            val ps = Pattern.compile(regex)

            if (!ps.matcher(source).matches()) {
                Toast.makeText(applicationContext, "영어만 입력해주세요", Toast.LENGTH_SHORT).show()
                return ""
            }
            return null
        }
    }
}
