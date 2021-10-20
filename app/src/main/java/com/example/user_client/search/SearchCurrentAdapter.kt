package com.example.user_client.search

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.user_client.R
import com.example.user_client.SearchDetailActivity
import com.example.user_client.dto.SearchData
import kotlin.coroutines.coroutineContext


class SearchCurrentAdapter(private val dataset: ArrayList<SearchData>) : RecyclerView.Adapter<SearchCurrentAdapter.SearchMainViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
    }
    private lateinit var mOnItemClickListener: OnItemClickListener

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        mOnItemClickListener = onItemClickListener
    }

    inner class SearchMainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateTime = view.findViewById<TextView>(R.id.search_datetime)
        val product = view.findViewById<TextView>(R.id.search_product)
        val textArea = view.findViewById<TextView>(R.id.search_textarea)
        val process = view.findViewById<TextView>(R.id.search_process)
        val imageButton = view.findViewById<ImageButton>(R.id.search_imageButton)

        init {
            view.setOnClickListener {
                val pos = adapterPosition
                if(pos != null && mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, pos)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_viewgroup, parent, false)
        return SearchMainViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchMainViewHolder, position: Int) {
        holder.dateTime.text = dataset[position].dateTime
        holder.product.text = dataset[position].product
        holder.textArea.text = dataset[position].textArea
        holder.process.text = dataset[position].process
    }

    override fun getItemCount(): Int = dataset.size
}