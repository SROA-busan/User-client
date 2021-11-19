package com.sroa.user_client.evalution

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sroa.user_client.databinding.EvaluationActivityBinding
import com.sroa.user_client.dto.WriteEvaluation
import com.sroa.user_client.network.RetrofitInstance
import com.sroa.user_client.search.SearchPreviousActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EvaluationActivity : AppCompatActivity() {
    private lateinit var _binding: EvaluationActivityBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = EvaluationActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setButtonEvent()
    }
    //전송 버튼
    private fun setButtonEvent(){
        val intent = intent
        val scheduleNum = intent.getLongExtra("scheduleNum", -1)

        Log.d("스케줄 번호", scheduleNum.toString())

        binding.evaluationInput.setOnClickListener {
            val writeContent = WriteEvaluation(
                binding.evaluationEditText.text.toString(),
                binding.evaluationScore.text.toString().toInt(),
                scheduleNum
            )
            Log.d("입력 값 : ", writeContent.toString())
            putEvaluation(writeContent)
        }
    }
    
    //평가 입력
    private fun putEvaluation(writeContent: WriteEvaluation){
        val putEval = RetrofitInstance().putEvaluation()

        putEval.writeEvaluation(writeContent).enqueue(object: Callback<Boolean>{
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                Log.d("평가 통신결과 : ", response.body().toString())
                startActivity(Intent(applicationContext, SearchPreviousActivity::class.java))
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.e("통신 실패 ", t.toString())
            }
        })
    }
}