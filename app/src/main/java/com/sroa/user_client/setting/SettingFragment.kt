package com.sroa.user_client.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sroa.user_client.MainActivity
import com.sroa.user_client.R

class SettingFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.nav_fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle("설정")
    }

    fun setTitle(title: String){
        //툴바 타이틀 설정
        val mMainactivity = activity as com.sroa.user_client.MainActivity
        mMainactivity.setTitle(title)
    }
}