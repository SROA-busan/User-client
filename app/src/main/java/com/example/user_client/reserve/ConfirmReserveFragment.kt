package com.example.user_client.reserve

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.user_client.MainActivity
import com.example.user_client.MainFragment
import com.example.user_client.R
import com.example.user_client.databinding.ReserveFragmentConfirmBinding

class ConfirmReserveFragment : Fragment(){
    private var binding : ReserveFragmentConfirmBinding? = null
    private val view get() = binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ReserveFragmentConfirmBinding.inflate(inflater, container, false)

        //버튼클릭 이벤트
        view.confirmButtonPrevious.setOnClickListener{
            val mMainActivity = activity as MainActivity
            mMainActivity.changeReserveFragment("select")
        } //이전
        return view.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}