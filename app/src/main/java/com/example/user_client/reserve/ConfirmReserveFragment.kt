package com.example.user_client.reserve

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.user_client.MainActivity
import com.example.user_client.MainFragment
import com.example.user_client.R
import com.example.user_client.databinding.ReserveFragmentConfirmBinding

class ConfirmReserveFragment : Fragment(), View.OnClickListener{
    private var binding : ReserveFragmentConfirmBinding? = null
    private val view get() = binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ReserveFragmentConfirmBinding.inflate(inflater, container, false)

        //버튼클릭 이벤트
        view.confirmButtonPrevious.setOnClickListener(this) //이전
        view.confirmButtonConfirm.setOnClickListener(this)  //확인
        return view.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onClick(v: View?) {
        val mMainActivity = activity as MainActivity
        when(v!!.id){
            R.id.confirm_button_confirm -> {
                //TODO 확인버튼 누르면 bottomNavigationBar의 홈버튼 클릭 이벤트를 실행하도록
            }
            R.id.confirm_button_previous -> {mMainActivity.changeReserveFragment("select")}
        }
    }
}