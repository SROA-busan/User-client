package com.sroa.user_client.reserve

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sroa.user_client.databinding.ReserveFragmentDetailBinding
import com.sroa.user_client.viewModel.ReserveViewModel

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

        //재예약시 뷰 변경
        setReservationDetailInput()
        //뒤로가기 이벤트
        setBackPressed()
        //버튼 이벤트
        setButtonEvent()
    }

    //뒤로가기 이벤트
    private fun setBackPressed() {
        val activity = activity as com.sroa.user_client.MainActivity
        activity.setHomeEnabled(true)
        activity.fragment = ReserveConfirmFragment()
    }

    //재예약시 뷰 변경
    private fun setReservationDetailInput(){
        //재예약
        if(viewModel.reReservation.value!!){
            binding.apply {
                textArea.visibility = View.INVISIBLE
                confirmEngineerAddress.visibility = View.INVISIBLE
                confirmServiceInfo.visibility = View.INVISIBLE
                confirmServiceCenterName.visibility = View.INVISIBLE
                confirmEngineerInfo.visibility = View.INVISIBLE
                confirmEngineerName.visibility = View.INVISIBLE
            }
        }
        //예약
        else{
            binding.apply {
                textArea.visibility = View.VISIBLE
                confirmEngineerAddress.visibility = View.VISIBLE
                confirmServiceInfo.visibility = View.VISIBLE
                confirmServiceCenterName.visibility = View.VISIBLE
                confirmEngineerInfo.visibility = View.VISIBLE
                confirmEngineerName.visibility = View.VISIBLE
            }
        }
    }

    //버튼클릭 이벤트
    private fun setButtonEvent() {
        //전화버튼
        binding.confirmButtonCall.setOnClickListener {
            Log.d("고객 전화번호", viewModel.engineerPhoneNumber.value.toString())
            //버튼 클릭시 전화번호 띄워주기기
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", viewModel.engineerPhoneNumber.value, null))
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}