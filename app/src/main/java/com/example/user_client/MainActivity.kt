package com.example.user_client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.user_client.databinding.MainActivityBinding
import com.example.user_client.reserve.ReserveConfirmFragment
import com.example.user_client.reserve.ReserveDetailFragment
import com.example.user_client.reserve.ReserveInputFragment
import com.example.user_client.reserve.ReserveSelectFragment
import com.example.user_client.search.SearchFragment
import com.example.user_client.setting.SettingFragment
import com.example.user_client.viewModel.ReserveViewModel
import java.lang.Exception
import kotlin.concurrent.fixedRateTimer

class MainActivity : AppCompatActivity() {

    private var binding: MainActivityBinding? = null
    val view get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(view.root)

        val viewModel = ViewModelProvider(this).get(ReserveViewModel::class.java)

        val intent = getIntent()
        val from = intent.getIntExtra("from", -1)
        val scheduleNum = intent.getLongExtra("scheduleNum", -1)
        Log.d("from", from.toString())

        //재 예약일경우
        if(from == 1) {
            //재예약 플래그
            viewModel.reReservation.value = true
            //스케줄 번호
            viewModel.scheduleNum.value = scheduleNum
            replaceFragment(ReserveInputFragment())
        }
        //그 외
        else{
            replaceFragment(MainFragment())
        }
        setActionBar()
        setNavFragment()
    }

    //툴바 설정
    private fun setActionBar(){
        val toolbar = view.mainToolBar
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar

        actionBar!!.setDisplayHomeAsUpEnabled(false) //왼쪽버튼 사용여부
        actionBar!!.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24) //왼쪽버튼 이미지 설정
    }

    //툴바 이름변경
    fun setTitle(title: String){
        view.mainToolBar.title = title
    }

    //툴바 메뉴버튼 설정
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main_toolbar_menu, menu) //main_toolbar_menu를 toolbar메뉴버튼으로 설정
//        return true
//    }

    fun setHomeEnabled(flag: Boolean){
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(flag) //왼쪽버튼 사용여부
    }


    lateinit var fragment: Fragment
    //툴바 메뉴 이벤트
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){
            android.R.id.home -> {
                if(fragment != null)
                    replaceFragment(fragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    //하단 네비
    private fun setNavFragment() {
        view.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                //홈
                R.id.home -> {
                    replaceFragment(MainFragment())
                    true
                }
                //예약
                R.id.reserve -> {
                    replaceFragment(ReserveInputFragment())
                    true
                }
                //조회
                R.id.search -> {
                    replaceFragment(SearchFragment())
                    true
                }
                //설정
                R.id.setting -> {
                    replaceFragment(SettingFragment())
                    true
                }
                else -> false
            }
        }
    }

    override fun onBackPressed() {

    }

    //예약메뉴 이동관련
    fun changeReserveFragment(fragmentName: String) {
        when (fragmentName) {
            "input" -> {
                replaceFragment(ReserveInputFragment())
            }
            "select" -> {
                replaceFragment(ReserveSelectFragment())
            }
            "confirm" -> {
                replaceFragment(ReserveConfirmFragment())
            }
            "detail" ->{
                replaceFragment(ReserveDetailFragment())
            }
        }
    }

    //프레그먼트 변경
    fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        //TODO replace를 쓰게되면 새로운 창을 여는 것 --> 이전상태가 저장이 안된다 --> 조회 -> 설정 -> 조회로 가면 조회가 초기화됨
        try{
            fragmentTransaction.replace(R.id.bottom_nav_frame, fragment)
            fragmentTransaction.commit()
        } catch (e : Exception){
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}