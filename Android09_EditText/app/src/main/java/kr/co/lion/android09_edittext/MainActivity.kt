package kr.co.lion.android09_edittext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import kr.co.lion.android09_edittext.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        layoutInflater : xml 파일을 통해서 View 객체를 생성할 수 있는 도구
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)


        // #### Viewbinding이 관리하는 View들 중 최상위 View를 설정하여 화면에 보여준다 ####

        // ViewBinding이 가지고 있는 root 프로퍼티는 가장 최상위에 있는 View를 지칭한다.
        // setContentView : 지칭한 View를 화면에 보여준다.
        setContentView(activityMainBinding.root)
        
        activityMainBinding.apply { 
            // EditText
            editTextText.apply { 
                // 문자열을 설정해준다.
                setText("코드로 설정한 문자열")

                // 문자열을 입력할 때 마다 동작한다.
                // it : EditText에 입력된 문자열을 가지고 온다.
                addTextChangedListener {
                    textView.text = "실시간 감시 : $it"
                }

                // 리턴키를 눌렀을 때 동작한다.
                setOnEditorActionListener { v, actionId, event ->

                    textView.text = "엔터키를 눌렀습니다\n"
                    textView.append("입력된 문자열 : $text")

                    // true : 리턴키를 누른 후 포커스가 현재 EditText로 유지된다.
                    // false : 리턴키를 누른 후 포커스가 다음 EditText로 이동한다.
                    false
                }
            }

            // button
            button.apply {
                setOnClickListener {
                    // EditText에서 문자열을 가져온다.
                    // Editable 타입으로 반환되므로 String 형태로 사용하고자 한다면 변환해줘야 한다.
                    // editTextText.text ==> Editable 타입
                    val str1 = editTextText.text.toString()
                    textView.text = str1
                }
            }
        }
    }
}