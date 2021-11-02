package com.example.user_client.reserve

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.user_client.MainActivity
import com.example.user_client.R
import com.example.user_client.databinding.ReserveFragmentInputBinding
import com.example.user_client.dto.ReserveData
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

    val binding get() = _binding!!

    //inflate만
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ReserveFragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    //실질적 구현
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //뷰모델 설정
        viewModel = ViewModelProvider(requireActivity()).get(ReserveViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setTitle("예약")
        setSpinner()
        setButtonEvent()
    }

    //툴바 이름설정
    fun setTitle(title: String) {
        val mMainactivity = activity as MainActivity
        mMainactivity.setTitle(title)
    }

    //스피너 설정
    fun setSpinner() {
        //리소스로 하는 방법
        ArrayAdapter.createFromResource(
            context!!,
            R.array.productList,
            android.R.layout.simple_dropdown_item_1line
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            binding.reserveProduct.adapter = adapter
        }
        //리스트로 하는 방법
//        val productList = arrayListOf<String>("냉장고", "스타일러")
//        val adapter = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, productList)
//
//        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
//        binding.reserveProduct.adapter = adapter
    }

    //id를 통해 서버에 저장된 이름, 주소, 비상연락망을 가져옴
    fun getUserInfo() {
        val getDataService = RetrofitInstance().getData()
        getDataService.getUserInfo(SignInActivity.id).apply {
            enqueue(object: Callback<UserInfo> {
                override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                    //TODO viewmodel에 이름, 주소, 비상연락망넣고 바인딩하기
                }

                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                    Log.d("상태 : ","콜백 실패")
                    Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show();
                }
            })
        }
    }

    //버튼 이벤트
    fun setButtonEvent() {
        //상위 인스턴스로 초기화
        val mainActivityView = activity as MainActivity

        binding.reserveButtonNext.setOnClickListener {
            setReserveData()
            //Fragment간 화면전환이 필요한 경우 속해있는 FragmentActivity의 FragmentManager을 통해 전환해줘야 한다.
            mainActivityView.changeReserveFragment("select")
        }
    }

    //livedata설정
    fun setReserveData() {
        viewModel.name.value = binding.reserveInputName.text.toString()
        viewModel.address.value = binding.reserveInputAddress.text.toString()
        viewModel.emergencyCall.value = binding.reserveInputEmergency.text.toString()
        viewModel.product.value = binding.reserveProduct.selectedItem.toString()
        viewModel.productInfo.value = binding.reserveProductInfo.text.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}