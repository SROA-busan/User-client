package com.example.user_client.reserve

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.user_client.MainActivity
import com.example.user_client.R
import com.example.user_client.databinding.LoginActivityBinding.inflate
import com.example.user_client.databinding.ReserveFragmentInputBinding
import java.util.zip.Inflater

class InputReserveFragment : Fragment() , View.OnClickListener{
    private var binding: ReserveFragmentInputBinding? = null
    val view get() = binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ReserveFragmentInputBinding.inflate(inflater, container, false)

        //Fragment는 Activity와 다르게 자체적으로 View.OnclickListener를 가지고있지 않으므로 상속받아 구현해줘야 한다.
        view.reserveButtonNext.setOnClickListener(this)
        return view.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onClick(v: View?) {
        Log.v("view : ", v.toString())
        when(v?.id){
            R.id.reserve_button_next -> {
                //MainActivity의 새 인스턴스로 초기화 하는 것이 아닌 올바른 상위 인스턴스로 초기화 해야한다.
                val mainActivityView = activity as MainActivity
                //Fragment간 화면전환이 필요한 경우 속해있는 FragmentActivity의 FragmentManager을 통해 전환해줘야 한다.
                mainActivityView.changeReserveFragment("select")
            }
        }
    }
}