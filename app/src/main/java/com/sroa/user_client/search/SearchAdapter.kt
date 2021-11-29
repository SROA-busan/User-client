package com.sroa.user_client.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sroa.user_client.R
import com.sroa.user_client.dto.CustomerResesrvationInfo


class SearchAdapter(private val dataset: ArrayList<CustomerResesrvationInfo>) : RecyclerView.Adapter<SearchAdapter.SearchDataViewHolder>() {
    //커스텀 리스너
    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
    }
    //객체 저장 변수
    private lateinit var mOnItemClickListener: OnItemClickListener

    //객체 전달변수
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        mOnItemClickListener = onItemClickListener
    }

    inner class SearchDataViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val dateTime = view.findViewById<TextView>(R.id.search_datetime)
        val product = view.findViewById<TextView>(R.id.search_product)
        val process = view.findViewById<TextView>(R.id.search_process)
        val imageButton = view.findViewById<ImageButton>(R.id.search_imageButton)

        init {
            view.setOnClickListener {
                val pos = adapterPosition

                if(pos != RecyclerView.NO_POSITION && mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, pos)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewgroup_search, parent, false)
        return SearchDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchDataViewHolder, position: Int) {
        var process = dataset[position].flag
        holder.product.text = dataset[position].productName
        holder.dateTime.text = dataset[position].startDate + " ~ " + dataset[position].endDate
//        0-> 예약완료 , 1 -> 처리 완료, 2 -> 수령, 3 -> 수리 완료, 4 -> 반납예약완료, 5-> 평가 완료
        //진행상황별 라벨색 변경
        when(process){
            0 -> {
                holder.process.setBackgroundResource(R.drawable.label_skyblue)
                holder.process.text = "예약완료"
            }
            1-> {
                holder.process.setBackgroundResource(R.drawable.label_green)
                holder.process.text = "처리완료"
            }
            2 -> {
                holder.process.setBackgroundResource(R.drawable.label_blue)
                holder.process.text = "수령"
            }
            3 -> {
                holder.process.setBackgroundResource(R.drawable.label_red)
                holder.process.text = "수리완료"
            }
            4 -> {
                holder.process.setBackgroundResource(R.drawable.label_yellow)
                holder.process.text = "반납예약완료"
            }
            5 -> {
                holder.process.setBackgroundResource(R.drawable.label_green)
                holder.process.text = "평가완료"
            }
        }
    }

    override fun getItemCount(): Int = dataset.size
}