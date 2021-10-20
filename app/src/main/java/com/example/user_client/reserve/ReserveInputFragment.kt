package com.example.user_client.reserve

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.user_client.MainActivity
import com.example.user_client.R
import com.example.user_client.databinding.ReserveFragmentInputBinding

class ReserveInputFragment : Fragment(){
    private var _binding: ReserveFragmentInputBinding? = null
    val binding get() = _binding!!

    //inflate만
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ReserveFragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    //실질적 구현
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle("예약")
        
        setButtonEvent()

    }
    //툴바 이름설정
    fun setTitle(title: String){
        val mMainactivity = activity as MainActivity
        mMainactivity.setTitle(title)
    }
    //버튼 이벤트
    fun setButtonEvent(){
        //MainActivity의 새 인스턴스로 초기화 하는 것이 아닌 올바른 상위 인스턴스로 초기화 해야한다.
        val mainActivityView = activity as MainActivity

        binding.reserveButtonNext.setOnClickListener{
            //Fragment간 화면전환이 필요한 경우 속해있는 FragmentActivity의 FragmentManager을 통해 전환해줘야 한다.
            mainActivityView.changeReserveFragment("select")
        }
        //이거 옮겨야되나?
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}