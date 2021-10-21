package com.example.user_client.viewPager

import android.transition.Slide
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.concurrent.CountDownLatch

class ViewPagerAdapter(fa: Fragment, count: Int) : FragmentStateAdapter(fa){
    var mCount = count

    override fun getItemCount(): Int = 2000 //최대한 많이 반복

    override fun createFragment(position: Int): Fragment {
        when(getRealPosition(position)){
            1 -> return SlideFragmentFirst()
            else -> return SlideFragmentSecond()
        }
    }

    private fun getRealPosition(position: Int) : Int{
        return position % 2
    }
}