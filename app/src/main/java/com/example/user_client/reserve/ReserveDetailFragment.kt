package com.example.user_client.reserve

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.user_client.MainActivity
import com.example.user_client.databinding.ReserveFragmentDetailBinding
import com.example.user_client.viewModel.ReserveViewModel

class ReserveDetailFragment : Fragment(){
    private var _binding : ReserveFragmentDetailBinding? = null
    private lateinit var viewModel : ReserveViewModel
    private val binding get() = _binding!!

    //inflate
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ReserveFragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    //구현
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //뷰모델, 데이터 바인딩
        viewModel = ViewModelProvider(requireActivity()).get(ReserveViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
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