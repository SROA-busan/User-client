package com.example.user_client.reserve

import android.opengl.Visibility
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.user_client.MainActivity
import com.example.user_client.MainFragment
import com.example.user_client.R
import com.example.user_client.databinding.ReserveFragmentReserveBinding
import com.example.user_client.network.RetrofitInstance
import com.example.user_client.viewModel.ReserveViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalDateTime

class ReserveSelectFragment : Fragment() {
    private var _binding: ReserveFragmentReserveBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ReserveViewModel
    private lateinit var buttonList: List<Button>

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

        //활성 비활성 설정을 위한 버튼 리스트
        buttonList = listOf(
            binding.button,
            binding.button2,
            binding.button3,
            binding.button4,
            binding.button5,
            binding.button6
        )

        //뒤로가기 이벤트
        setBackPressed()
        //달력 날짜변경 이벤트
        setCalendarEvent()
    }

    //뒤로가기 이벤트
    private fun setBackPressed(){
        val activity = activity as MainActivity
        activity.setHomeEnabled(true)
        activity.fragment = ReserveInputFragment()
    }

    fun setCalendarEvent(){
        //달력 날짜변경 이벤트
        binding.calendarView2.setOnDateChangeListener { view, year, month, dayOfMonth ->

            //날짜 형식 :: 2021-11-09
            lateinit var localDate: String
            if (dayOfMonth < 10)
                localDate = "${year}-${month+1}-0${dayOfMonth}"
            else
                localDate = "${year}-${month+1}-${dayOfMonth}"

            //재예약
            if(viewModel.reReservation.value!!){
                Log.d("reReservation : ", viewModel.reReservation.value.toString())
                findAvailableTimeForReturn(localDate, dayOfMonth)
            }
            //새 예약
            else{
                binding.tableLayout.visibility = View.VISIBLE
                Log.d("reReservation : ", viewModel.reReservation.value.toString())
                //고객 주소기반 이용가능한 시간 조회(달력 선택일 + 버튼에 적힌 시간, 고객 주소)
                findAvailableTime(localDate, viewModel.address.value!!, dayOfMonth)
            }

            //버튼 터치 이벤트(달력 선택일)
            setButtons(localDate)
        }
    }

    /* 고객 날짜 선택시 예약 가능 현황 조회
    * [ 09:00 ,10:30, 12:30, 14:00, 15:30, 17:00] */
    private fun findAvailableTime(date: String, address: String, dayOfMonth: Int) {
        //일정 예약 인스턴스 생성
        val getData = RetrofitInstance().getReservationSchedule()
        //통신
        getData.findAvailableTime(date, address).enqueue(object : Callback<List<Boolean>> {
            override fun onResponse(call: Call<List<Boolean>>, response: Response<List<Boolean>>) {
                Log.d("예약 통신 성공", response.body().toString())
                val list: List<Boolean> = response.body()!!

                //버튼별 활성&비활성화
                for (i in list.indices) {
                    buttonList[i].isEnabled = list[i]
                }
                //당일일경우 1시간 이전은 예약불가
                isToday(dayOfMonth)
            }

            override fun onFailure(call: Call<List<Boolean>>, t: Throwable) {
                Log.e("통신실패 !! ", "에러명 ${t}")
            }
        })
    }
    
    //고객 재예약 정보
    fun findAvailableTimeForReturn(date: String, toDay: Int){
        val reservationService = RetrofitInstance().getReservationSchedule()
        reservationService.findAvailableTimeForReturn(viewModel.scheduleNum.value!!, date).enqueue(object : Callback<List<Boolean>>{
            override fun onResponse(call: Call<List<Boolean>>, response: Response<List<Boolean>>) {
                Log.d("재예약 통신성공", response.body().toString())
                val list: List<Boolean> = response.body()!!

                //버튼별 활성&비활성화
                for (i in list.indices) {
                    buttonList[i].isEnabled = list[i]
                }
                showTable(toDay)
            }

            override fun onFailure(call: Call<List<Boolean>>, t: Throwable) {
                Log.e("통신 실패 : ", t.toString())
            }
        })
    }

    //당일 예약 검사
    private fun isToday(dayOfMonth: Int){
        //당일 시간 + 1시간인 버튼은 비활성화
        val date = LocalDateTime.now().toString().substring(0, 16).split("-", "T", ":")
        Log.d("오늘", date.toString())
        //당일 예약
        if (date.get(2).equals(dayOfMonth.toString())){
            Log.d("당일예약 : ", "true")
            //현재시간 + 1보다 버튼의 시간이 작을경우 사용불가능
            buttonList.forEach {
                if(it.text.split(":").get(0).toInt() < date.get(1).toInt()+1){
                    it.isEnabled = false
                }
            }
        }
    }

    //당일포함 이전날일시 테이블 안보임
    private fun showTable(toDay: Int){
        val date = LocalDateTime.now().toString().substring(0, 16).split("-", "T", ":")
        Log.d("달력 오늘 ", toDay.toString())
        Log.d("진짜 오늘 ", date.toString())
        if(date.get(2).toInt() >= toDay){
            Log.d("당일 재예약 : ", "true")
            binding.tableLayout.visibility = View.INVISIBLE
            Toast.makeText(context, "당일, 이전예약은 할 수 없습니다.", Toast.LENGTH_SHORT).show()
        }
        else{
            binding.tableLayout.visibility = View.VISIBLE
        }
    }

    //버튼별 이벤트 설정
    private fun setButtons(date: String){
        binding.button.setOnClickListener {
            setButtonEvent(date, binding.button.text.toString())
        }
        binding.button2.setOnClickListener {
            setButtonEvent(date, binding.button2.text.toString())
        }
        binding.button3.setOnClickListener {
            setButtonEvent(date, binding.button3.text.toString())
        }
        binding.button4.setOnClickListener {
            setButtonEvent(date, binding.button4.text.toString())
        }
        binding.button5.setOnClickListener {
            setButtonEvent(date, binding.button5.text.toString())
        }
        binding.button6.setOnClickListener {
            setButtonEvent(date, binding.button6.text.toString())
        }
    }

    //버튼 이벤트 셋팅
    private fun setButtonEvent(selectedDate: String, time: String) {
        val mMainactivity = activity as MainActivity
        //버튼을 누른 날짜
        // 2021-11-09 15:30
        viewModel.confirmDateTime.value = "${selectedDate} ${time}"
        mMainactivity.changeReserveFragment("confirm")
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}