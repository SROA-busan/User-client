package com.example.user_client

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.user_client.R
import com.example.user_client.databinding.NavFragmentMainBinding
import com.example.user_client.viewPager.ViewPagerAdapter
import java.util.*

class MainFragment : Fragment() {

    private var binding: NavFragmentMainBinding? = null
    private val view get() = binding!!
    var currentPage = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = NavFragmentMainBinding.inflate(inflater, container, false)
        //툴바 타이틀 설정
        val mMainactivity = activity as MainActivity
        mMainactivity.setTitle("홈")

        view.viewPager.adapter = ViewPagerAdapter(this, 1)
        view.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        //페이지 자동 전환
        val handler = Handler(Looper.getMainLooper())
        val update = Runnable {
            view.viewPager.setCurrentItem(currentPage++, true)
        }
        val timer = Timer()
        timer.schedule(object : TimerTask(){
            override fun run() {
                handler.post(update)
            }

        }, 500, 3000)
        return view.root
    }

//    private inner class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
//        override fun getItemCount(): Int = 2
//
//        override fun createFragment(position: Int): Fragment {
//            var returnFrag = Fragment()
//
//            when (position) {
//                0 -> returnFrag = SlideFragmentFirst()
//                1 -> returnFrag = SlideFragmentSecond()
//            }
//            return returnFrag
//        }
//    }
}