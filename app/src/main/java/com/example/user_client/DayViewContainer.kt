package com.example.user_client

import android.view.View
import com.example.user_client.databinding.CalendarDayLayoutBinding
import com.kizitonwose.calendarview.ui.ViewContainer

class DayViewContainer(view: View) : ViewContainer(view) {
    val textView = CalendarDayLayoutBinding.bind(view).calendarDayText
}