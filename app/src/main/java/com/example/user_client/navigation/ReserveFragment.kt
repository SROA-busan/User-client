package com.example.user_client.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.user_client.R
import com.example.user_client.databinding.NavFragmentReserveBinding
import com.example.user_client.viewPager.SlideFragmentFirst
import com.example.user_client.viewPager.SlideFragmentSecond

class ReserveFragment : Fragment() {
    private var binding: NavFragmentReserveBinding? = null
    private val view get() = binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.v("ReserveFragment", "yes")
        binding = NavFragmentReserveBinding.inflate(inflater, container, false)
//        view.viewpager.adapter = ViewPagerAdapter(this)
        return inflater.inflate(R.layout.nav_fragment_reserve, container, false)
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