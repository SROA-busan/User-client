package com.example.user_client.reserve

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.user_client.MainActivity
import com.example.user_client.R
import com.example.user_client.databinding.ReserveFragmentReserveBinding

class ReserveSelectFragment :Fragment(){
    private var _binding: ReserveFragmentReserveBinding? = null
    private val binding get() = _binding!!

    //inflate
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ReserveFragmentReserveBinding.inflate(inflater, container, false)
        return binding.root
    }

    //init
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtonEvent()
    }

    //버튼 이벤트 셋팅
    fun setButtonEvent(){
        val mMainactivity = activity as MainActivity
        binding.buttonNext.setOnClickListener{
            mMainactivity.changeReserveFragment("confirm")
        }
        binding.buttonBefore.setOnClickListener{
            mMainactivity.changeReserveFragment("input")
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}