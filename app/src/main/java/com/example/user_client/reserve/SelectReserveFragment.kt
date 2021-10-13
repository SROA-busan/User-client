package com.example.user_client.reserve

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.user_client.R

class SelectReserveFragment :Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.reserve_fragment_reserve, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}