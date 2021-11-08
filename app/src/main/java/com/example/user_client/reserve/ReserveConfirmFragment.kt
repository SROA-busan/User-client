package com.example.user_client.reserve

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.user_client.MainActivity
import com.example.user_client.databinding.ReserveFragmentConfirmBinding

class ReserveConfirmFragment: Fragment() {
    private var _binding: ReserveFragmentConfirmBinding? = null
    private val binding get() = _binding!!

    //inflate
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ReserveFragmentConfirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.reserveConfirmButton.setOnClickListener {
            val mMainActivity = activity as MainActivity
            mMainActivity.changeReserveFragment("detail")
        }
    }

    //TODO 예약하기
    //TODO Detail페이지에 예약정보 + 엔지니어 이름 + 서비스 센터 주소 보내기

    override fun onDestroyView() {
        super.onDestroyView()
    }
}