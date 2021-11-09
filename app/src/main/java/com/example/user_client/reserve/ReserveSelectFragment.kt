package com.example.user_client.reserve

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.user_client.MainActivity
import com.example.user_client.R
import com.example.user_client.databinding.ReserveFragmentReserveBinding
import com.example.user_client.network.RetrofitInstance
import com.example.user_client.viewModel.ReserveViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class ReserveSelectFragment : Fragment() {
    private var _binding: ReserveFragmentReserveBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ReserveViewModel

    //inflate
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ReserveFragmentReserveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //뷰모델, 데이터 바인딩
        viewModel = ViewModelProvider(requireActivity()).get(ReserveViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        //고객 주소기반 이용가능한 시간 조회(달력날짜 + 버튼에 적힌 시간, 고객 주소)
        findAvailableTime(LocalDate.now().toString(), viewModel.address.value!!)
        //버튼 터치 이벤트
        setButtonEvent()
    }

    /* 고객 날짜 선택시 예약 가능 현황 조회
    * [ 09:00 ,10:30, 12:30, 14:00, 15:30, 17:00] */
    private fun findAvailableTime(date: String, address: String) {
        //일정 예약 인스턴스 생성
        val getData = RetrofitInstance().getReservationSchedule()
        //통신
        getData.findAvailableTime(date, address).enqueue(object : Callback<List<Boolean>> {
            override fun onResponse(call: Call<List<Boolean>>, response: Response<List<Boolean>>) {
                val list: List<Boolean> = response.body()!!
                //활성 비활성 설정을 위한 버튼 리스트
                val buttonList: List<Button> = listOf(
                    binding.button,
                    binding.button2,
                    binding.button3,
                    binding.button4,
                    binding.button5,
                    binding.button6
                )
                //버튼별 활성&비활성화
                for (i in list.indices) {
                    buttonList[i].isEnabled = list[i]
                }
            }

            override fun onFailure(call: Call<List<Boolean>>, t: Throwable) {
                Log.e("통신실패 !! ", "에러명 ${t}")
            }
        })

    }

    //TODO 반납 예약

    //버튼 이벤트 셋팅
    private fun setButtonEvent() {
        val mMainactivity = activity as MainActivity
        binding.buttonNext.setOnClickListener {
            mMainactivity.changeReserveFragment("confirm")
        }
        binding.buttonBefore.setOnClickListener {
            mMainactivity.changeReserveFragment("input")
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}