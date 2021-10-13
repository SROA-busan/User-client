package com.example.user_client.reserve

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.user_client.MainActivity
import com.example.user_client.R
import com.example.user_client.databinding.ReserveFragmentInputBinding
import com.example.user_client.databinding.ReserveFragmentReserveBinding

class SelectReserveFragment :Fragment(), View.OnClickListener {
    private var binding: ReserveFragmentReserveBinding? = null
    private val view get() = binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ReserveFragmentReserveBinding.inflate(inflater, container, false)
        view.buttonNext.setOnClickListener(this)
        return view.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onClick(v: View?) {
        val mMainactivity = activity as MainActivity
        when(v!!.id){
            R.id.button_next -> {
                mMainactivity.changeReserveFragment("confirm")
            }
            R.id.button_before -> {
                mMainactivity.changeReserveFragment("input")
            }
        }
    }
}