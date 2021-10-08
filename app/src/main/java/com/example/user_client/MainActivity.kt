package com.example.user_client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.user_client.databinding.MainActivityBinding
import com.example.user_client.navigation.ReserveFragment
import com.example.user_client.navigation.SearchFragment
import com.example.user_client.navigation.SettingFragment

class MainActivity : AppCompatActivity() {

    private var binding:MainActivityBinding? = null
    private val view get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(view.root)

        replaceFragment(ReserveFragment())
        setFragment()
    }

    private fun setFragment(){
        view.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.reserve -> {
                    replaceFragment(ReserveFragment())
                    true
                }
                R.id.search -> {
                    replaceFragment(SearchFragment())
                    true
                }
                R.id.setting -> {
                    replaceFragment(SettingFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        //TODO replace를 쓰게되면 새로운 창을 여는 것 --> 이전상태가 저장이 안된다 --> 조회 -> 설정 -> 조회로 가면 조회가 초기화됨
        fragmentTransaction.replace(R.id.bottom_nav_frame, fragment)
        fragmentTransaction.commit()
    }
}