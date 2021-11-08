package com.example.user_client.reserve

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.user_client.MainActivity
import com.example.user_client.R
import com.example.user_client.databinding.ReserveFragmentInputBinding
import com.example.user_client.dto.UserInfo
import com.example.user_client.network.RetrofitInstance
import com.example.user_client.sign.SignInActivity
import com.example.user_client.viewModel.ReserveViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReserveInputFragment : Fragment() {
    private var _binding: ReserveFragmentInputBinding? = null
    private lateinit var viewModel: ReserveViewModel
    private val binding get() = _binding!!

    //inflate
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ReserveFragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //뷰모델 설정
        viewModel = ViewModelProvider(requireActivity()).get(ReserveViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        //타이틀 설정
        setToolBarTitle("예약")
        //스피너 설정
        setSpinner()
        //저장된 사용자의 정보 호출(이름, 주소, 전화번호)
        getUserInfo(SignInActivity.userId)
        //버튼 이벤트 할당
        setButtonEvent()
    }

    //툴바 이름설정
    private fun setToolBarTitle(title: String) {
        val mMainactivity = activity as MainActivity
        mMainactivity.setTitle(title)
    }

    //스피너 설정
    private fun setSpinner() {
        ArrayAdapter.createFromResource(
            context!!,
            R.array.productList,
            android.R.layout.simple_dropdown_item_1line
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            binding.reserveProduct.adapter = adapter
        }
    }

    //저장된 사용자의 정보 호출(이름, 주소, 전화번호)
    private fun getUserInfo(userId: String) {
        //데이터 조회 인스턴스 생성
        val getDataService = RetrofitInstance().getData()
        //통신
        getDataService.getUserInfo(userId).enqueue(object : Callback<UserInfo> {
            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                if (response.body() != null) {
                    //가져온 정보를 뷰모델에 할당
                    response.body()!!.apply {
                        viewModel.customerName.value = name
                        viewModel.address.value = address
                        viewModel.phoneNumber.value = phoneNum
                    }
                }
            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                Log.e("통신실패 !! ", "에러명 ${t}")
            }
        })

    }

    //버튼 이벤트
    private fun setButtonEvent() {
        //상위 인스턴스로 초기화
        val mainActivityView = activity as MainActivity

        binding.reserveButtonNext.setOnClickListener {
            //livedata설정
            setReserveData()
            //Fragment간 화면전환이 필요한 경우 속해있는 FragmentActivity의 FragmentManager을 통해 전환해줘야 한다.
            mainActivityView.changeReserveFragment("select")
        }
    }

    //livedata설정
    private fun setReserveData() {
        viewModel.customerName.value = binding.reserveInputName.text.toString()
        viewModel.address.value = binding.reserveInputAddress.text.toString()
        viewModel.phoneNumber.value = binding.reserveInputEmergency.text.toString()
        viewModel.classifyName.value = binding.reserveProduct.selectedItem.toString()
        viewModel.content.value = binding.reserveProductInfo.text.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}