package com.example.user_client.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.user_client.R
import com.example.user_client.dto.SearchData


class SearchAdapter(private val dataset: ArrayList<SearchData>) : RecyclerView.Adapter<SearchAdapter.SearchDataViewHolder>() {
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
        val textArea = view.findViewById<TextView>(R.id.search_textarea)
        val process = view.findViewById<TextView>(R.id.search_process)

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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_viewgroup, parent, false)
        return SearchDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchDataViewHolder, position: Int) {
        var process = dataset[position].process
        holder.dateTime.text = dataset[position].dateTime
        holder.product.text = dataset[position].product
        holder.textArea.text = dataset[position].productInfo
        holder.process.text = process
        //진행상황별 라벨색 변경
        when(process){
            "진행중" -> holder.process.setBackgroundResource(R.drawable.label_blue)
            "입고완료", "상세보기" -> holder.process.setBackgroundResource(R.drawable.label_green)
            "예약대기" -> holder.process.setBackgroundResource(R.drawable.label_red)
        }
    }

    override fun getItemCount(): Int = dataset.size
}