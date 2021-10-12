package com.example.user_client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.user_client.databinding.MainActivityBinding
//import com.example.user_client.reserve.InputReserveFragment
//import com.example.user_client.reserve.SelectReserveFragment
import com.example.user_client.search.SearchFragment
import com.example.user_client.setting.SettingFragment

class MainActivity : FragmentActivity() {

    private var binding:MainActivityBinding? = null
    private val view get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(view.root)

        replaceFragment(MainFragment())
        setNavFragment()
    }

    private fun setNavFragment(){
        view.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.reserve -> {
//                    replaceFragment(InputReserveFragment())
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

    fun changeReserveFragment(fragmentName: String){
        when(fragmentName){
//            "select" -> replaceFragment(SelectReserveFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        //TODO replace를 쓰게되면 새로운 창을 여는 것 --> 이전상태가 저장이 안된다 --> 조회 -> 설정 -> 조회로 가면 조회가 초기화됨
        fragmentTransaction.replace(R.id.bottom_nav_frame, fragment)
        fragmentTransaction.commit()
    }
}