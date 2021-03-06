package com.example.user_client

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.user_client.databinding.CalendarDayLayoutBinding
import com.example.user_client.databinding.NavFragmentMainBinding
import com.example.user_client.viewPager.ViewPagerAdapter
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.model.ScrollMode
import com.kizitonwose.calendarview.ui.DayBinder
import java.time.DayOfWeek
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.*

class MainFragment : Fragment() {

    private var _binding: NavFragmentMainBinding? = null
    private val binding get() = _binding!!
    private var currentPage = 0
    private val timer = Timer()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = NavFragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //툴바 타이틀 설정
        setTitle()
        //뷰 페이저 설정
        setViewPager(timer)
        //캘린더 설정
        val currentMonth = YearMonth.now()                      //현재월
        val firstOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek //첫 주

        setCalendarSchedule(currentMonth, firstOfWeek)
    }

    //툴바 타이틀 설정
    private fun setTitle() {
        val mMainactivity = activity as MainActivity
        mMainactivity.setTitle("홈")
    }

    //뷰 페이저 설정
    private fun setViewPager(timer: Timer) {
        binding.viewPager.adapter = ViewPagerAdapter(this, 1)
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        //페이지 자동 전환
        val handler = Handler(Looper.getMainLooper())
        val update = Runnable {
            binding.viewPager.setCurrentItem(currentPage++, true)
        }

        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 0, 3000)
    }

    //캘린더뷰 설정
    fun setCalendarSchedule(currentMonth: YearMonth, firstOfWeek: DayOfWeek) {
        //달력에 날짜 텍스트뷰 바인딩
        binding.calendarView.dayBinder = object : DayBinder<DayViewContainer> {

            override fun bind(container: DayViewContainer, day: CalendarDay) {
                //날짜 바인드
                container.textView.text = day.date.dayOfMonth.toString()
                /*
                * inDates have their owner property set to DayOwner.PREVIOUS_MONTH
                * outDates have their owner property set to DayOwner.NEXT_MONTH
                * monthDates have their owner property set to DayOwner.THIS_MONTH
                */
                //inDates
                if (day.owner == DayOwner.THIS_MONTH) {
                    container.textView.setTextColor(Color.GRAY) //회색
                }
                //outDates
                else {
                    container.textView.setTextColor(Color.TRANSPARENT) //투명
                }
            }

            override fun create(view: View): DayViewContainer = DayViewContainer(view)
        }
        
        binding.calendarView.apply {
            //월 설정
            setup(currentMonth.minusMonths(10), currentMonth.plusMonths(10), firstOfWeek)
            //처음 표시할 월
            scrollToMonth(currentMonth)
            //스크롤 방식
            scrollMode = ScrollMode.PAGED
        }
    }

    override fun onPause() {
        super.onPause()

        timer.cancel()
        timer.purge()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}