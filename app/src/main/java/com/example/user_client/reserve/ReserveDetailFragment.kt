package com.example.user_client.reserve

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.user_client.MainActivity
import com.example.user_client.databinding.ReserveFragmentDetailBinding
import com.example.user_client.dto.ReserveData
import com.example.user_client.network.RetrofitInstance
import com.example.user_client.sign.SignInActivity
import com.example.user_client.viewModel.ReserveViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class ReserveDetailFragment : Fragment() {
    private var _binding: ReserveFragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ReserveViewModel

    //inflate
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ReserveFragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //뷰모델, 데이터 바인딩
        viewModel = ViewModelProvider(requireActivity()).get(ReserveViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        //버튼 이벤트
        setButtonEvent()
    }



    //버튼클릭 이벤트
    private fun setButtonEvent() {
        //전화버튼
        binding.confirmButtonCall.setOnClickListener {
            //버튼 클릭시 전화번호 띄워주기기
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "01040275019", null))
            startActivity(intent)
        }
        //이전 버튼
        binding.confirmButtonPrevious.setOnClickListener {
            val mMainActivity = activity as MainActivity
            mMainActivity.changeReserveFragment("select")
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}