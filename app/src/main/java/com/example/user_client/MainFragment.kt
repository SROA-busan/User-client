package com.example.user_client

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.user_client.R
import com.example.user_client.databinding.NavFragmentMainBinding

class MainFragment : Fragment() {
    private var binding: NavFragmentMainBinding? = null
    private val view get() = binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.v("ReserveFragment", "yes")
        binding = NavFragmentMainBinding.inflate(inflater, container, false)
//        view.viewpager.adapter = ViewPagerAdapter(this)
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