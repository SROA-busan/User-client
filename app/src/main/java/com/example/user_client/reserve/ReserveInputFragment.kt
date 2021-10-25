package com.example.user_client.reserve

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.user_client.MainActivity
import com.example.user_client.R
import com.example.user_client.databinding.ReserveFragmentInputBinding
import com.example.user_client.dto.ReserveData
import com.example.user_client.viewModel.ReserveViewModel

class ReserveInputFragment : Fragment() {
    private var _binding: ReserveFragmentInputBinding? = null
    private lateinit var viewModel: ReserveViewModel

    val binding get() = _binding!!

    //inflate만
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        _binding = ReserveFragmentInputBinding.inflate(inflater, container, false)
        //데이터 바인딩
        _binding = DataBindingUtil.inflate(inflater, R.layout.reserve_fragment_input, container, false)
        viewModel = ViewModelProvider(this).get(ReserveViewModel::class.java)

        //뷰모델 설정
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        return binding.root
    }

    //실질적 구현
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val userModel : ReserveViewModel by viewModels()


        setTitle("예약")
        setSpinner()
        setButtonEvent()

    }

    //툴바 이름설정
    fun setTitle(title: String) {
        val mMainactivity = activity as MainActivity
        mMainactivity.setTitle(title)
    }

    //버튼 이벤트
    fun setButtonEvent() {
        //MainActivity의 새 인스턴스로 초기화 하는 것이 아닌 올바른 상위 인스턴스로 초기화 해야한다.
        val mainActivityView = activity as MainActivity

        binding.reserveButtonNext.setOnClickListener {
            setReserveData()
            //TODO 뷰모델의 기본값출력 ok, 기본값 화면에 표시 ok, 화면에서 입력받은 값 가져오는거 안됨
            //화면 변경사항이 observe가 안된다 == 데이터 바인딩이 안됬다
            viewModel.getName().observe(this, Observer {
//                Toast.makeText(context, it+"test", Toast.LENGTH_SHORT).show()
                Log.d("test ", it+"뷰모델 값")
            })

            viewModel.setName("tttest")
            //Fragment간 화면전환이 필요한 경우 속해있는 FragmentActivity의 FragmentManager을 통해 전환해줘야 한다.
//            mainActivityView.changeReserveFragment("select")
        }
    }
    
    //스피너 설정
    fun setSpinner() {
        //리소스로 하는 방법
//        ArrayAdapter.createFromResource(
//            context!!,
//            R.array.productList,
//            android.R.layout.simple_dropdown_item_1line
//        ).also { adapter ->
//            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
//            binding.reserveProduct.adapter = adapter
//        }
        //리스트로 하는 방법
        val productList = arrayListOf<String>("냉장고", "스타일러")
        val adapter = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, productList)

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        binding.reserveProduct.adapter = adapter
    }

    //dto 생성
    //TODO databinding으로 변경
    fun setReserveData(): ReserveData {

        val reserveData = ReserveData(
            binding.reserveInputName.text.toString(),
            binding.reserveInputAddress.text.toString(),
            binding.reserveInputEmergency.text.toString(),
            binding.reserveProduct.selectedItem.toString(),
            binding.reserveProductInfo.text.toString()
        )
        return reserveData
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}