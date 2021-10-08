package com.example.user_client.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.user_client.R

class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.v("SearchFragment", "yes")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.nav_fragment_search, container, false)
    }

}