package com.example.user_client.search

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.user_client.MainActivity
import com.example.user_client.databinding.SearchFragmentMainBinding
import com.example.user_client.dto.SearchData

class SearchFragment : Fragment() {
    companion object {
        val dataset = ArrayList<SearchData>()
    }

    private var _binding: SearchFragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchFragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle("조회")
        //버튼 이벤트 설정
        setButtonEvent()
        //recyclerView 설정
        setRecyclerView()
    }

    //툴바 타이틀 설정
    fun setTitle(title: String) {
        val mMainactivity = activity as MainActivity
        mMainactivity.setTitle(title)
    }

    //recyclerView 호출
    fun setRecyclerView() {
        val mRecyclerView = binding.searchRecyclerMain
        val mSearchMain = SearchData()
        dataset.add(mSearchMain)
        //어댑터 설정
        mRecyclerView.adapter = SearchAdapter(dataset)
        //layoutManager 설정
        mRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    fun setButtonEvent(){
        //예약현황 버튼
        binding.reserveMain.setOnClickListener {
            //내역
            if (binding.searchRecyclerMain.isVisible) {
                binding.searchRecyclerMain.visibility = View.GONE
                //버튼 화살표 애니메이션
                binding.arrow.animate().rotation(0f)
            } else {
                binding.searchRecyclerMain.visibility = View.VISIBLE
                binding.arrow.animate().rotation(90f)
            }
        }
        //수리현황 메뉴
        binding.reserveCurrent.setOnClickListener {
            startActivity(Intent(context, SearchCurrentActivity::class.java))
        }
        //이전수리 메뉴
        binding.reservePrevious.setOnClickListener {
            startActivity(Intent(context, SearchPreviousActivity::class.java))
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}