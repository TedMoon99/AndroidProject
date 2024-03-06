package kr.co.lion.question1

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kr.co.lion.question1.databinding.ActivityMainBinding

/*
문재1)

화면에 입력 요소 두 개와 버튼 4개, 문자열 출력 요소 한개가 있다.
버튼은 더하기, 빼기, 곱하기, 나누기 이렇게 4개이다.
입력 요소 각각에 정수값을 입력하고 버튼을 누르면
버튼에 해당하는 연산을 수행하여 그 결과를 문자열 출력 요소에 출력한다.
 */
class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        Log.d("test", "Binding Success!!")
        setEvent()
    }

    // Event 설정
    fun setEvent(){
        activityMainBinding.apply {
            buttonPlus.setOnClickListener {
                // 입력요소 받아오기
                val a : String = firstOperand.text.toString()
                val b  = secondOperand.text.toString()
                if (a.isNotEmpty() && b.isNotEmpty()){
                    val temp1 = a.toInt()
                    val temp2 = b.toInt()

                    val temp = temp1 + temp2

                    textViewResult.text = "A + B = ${temp}"
                    Log.d("test", "Click Success!!")
                }
            }

            buttonMinus.setOnClickListener {
                // 입력요소 받아오기
                val a : String = firstOperand.text.toString()
                val b  = secondOperand.text.toString()
                if (a.isNotEmpty() && b.isNotEmpty()){
                    val temp1 = a.toInt()
                    val temp2 = b.toInt()

                    val temp = temp1 - temp2

                    textViewResult.text = "A - B = ${temp}"
                    Log.d("test", "Click Success!!")
                }
            }
            buttonMultiple.setOnClickListener {
                // 입력요소 받아오기
                val a : String = firstOperand.text.toString()
                val b  = secondOperand.text.toString()
                if (a.isNotEmpty() && b.isNotEmpty()){
                    val temp1 = a.toInt()
                    val temp2 = b.toInt()

                    val temp = temp1 * temp2

                    textViewResult.text = "A * B = ${temp}"
                    Log.d("test", "Click Success!!")
                }
            }
            buttonDivide.setOnClickListener {
                // 입력요소 받아오기
                val a : String = firstOperand.text.toString()
                val b  = secondOperand.text.toString()
                if (a.isNotEmpty() && b.isNotEmpty()){
                    val temp1 = a.toFloat()
                    val temp2 = b.toFloat()

                    val temp = temp1 / temp2

                    textViewResult.text = "A / B = ${temp}"
                    Log.d("test", "Click Success!!")
                }
            }
        }
    }
}