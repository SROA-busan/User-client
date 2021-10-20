package com.example.user_client.reserve

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.user_client.MainActivity
import com.example.user_client.databinding.ReserveFragmentDetailBinding

class ReserveDetailFragment : Fragment(){
    private var _binding : ReserveFragmentDetailBinding? = null
    private val binding get() = _binding!!

    //inflate
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ReserveFragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    //구현
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtonEvent()
    }

    //버튼클릭 이벤트
    fun setButtonEvent(){
        binding.confirmButtonPrevious.setOnClickListener{
            val mMainActivity = activity as MainActivity
            mMainActivity.changeReserveFragment("select")
        } //이전
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}