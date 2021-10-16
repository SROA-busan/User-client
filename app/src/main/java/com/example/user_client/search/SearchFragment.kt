package com.example.user_client.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.user_client.R
import com.example.user_client.databinding.SearchFragmentMainBinding

class SearchFragment : Fragment() {
    private var binding : SearchFragmentMainBinding? = null
    private val view get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentMainBinding.inflate(inflater, container, false)


        //예약현황
        view.reserveMain.setOnClickListener{
            //내역
            if(view.searchRecyclerMain.isVisible){
                view.searchRecyclerMain.visibility = View.GONE
                //버튼 화살표
                view.arrow.animate().rotation(0f)
            }
            else{
                view.searchRecyclerMain.visibility = View.VISIBLE
                view.arrow.animate().rotation(90f)
            }
        }

        return view.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}