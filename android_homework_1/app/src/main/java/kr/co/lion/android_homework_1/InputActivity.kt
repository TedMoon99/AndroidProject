package kr.co.lion.android_homework_1

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import kr.co.lion.android_homework_1.databinding.ActivityInputBinding
import kotlin.concurrent.thread

class InputActivity : AppCompatActivity() {

    lateinit var  activityInputBinding : ActivityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityInputBinding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(activityInputBinding.root)

        }

    // 툴바 설정
    fun setToolbar(){
        activityInputBinding.apply {
            toolbarInput.apply {
                // 타이틀
                title = "학생 정보 입력"
                // 뒤로가기
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
                // 메뉴
                inflateMenu(R.menu.menu_input)
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menu_input_done -> {
                            processInputDone()
                        }
                    }
                    true
                }
            }
        }


    }

    // View 설정
    fun setView(){
        activityInputBinding.apply {
            // 뷰에 포커스를 준다
            textFieldInputName.requestFocus()
            // 키보드를 올린다.
            // 이 때, View를 지정해야 한다.

            showSoftInput(textFieldInputName)

            // 수학 입력칸
            // 엔터키를 누르면 입력 완료 처리를 한다.
            textFieldInputMath.setOnEditorActionListener{ v, actionId, event ->
                processInputDone()
                true
            }
        }

    }

    // 입력 완료 처리
    fun processInputDone() {
        activityInputBinding.apply {
            // 사용자가 입력한 내용을 가져온다
            val name = textFieldInputName.text.toString()
            val gradeStr = textFieldInputGrade.text.toString()
            val korStr = textFieldInputKor.text.toString()
            val engStr = textFieldInputEng.text.toString()
            val mathStr = textFieldInputMath.text.toString()

            // 입력 검사
            if (name.isEmpty()) {
                showDialog("이름 입력 오류", "이름을 입력해주세요", textFieldInputName)
                return
            }
        }
    }

    // 다이얼로그를 보여주는 메서드
    fun showDialog(title:String, message:String, focusView: TextInputEditText){
        // 다이얼로그를 보여준다.
        val builder = MaterialAlertDialogBuilder(this@InputActivity).apply{
            setTitle(title)
            setMessage(message)
            setPositiveButton("확인"){ dialogInterface: DialogInterface, i : Int ->
                focusView.setText("")
                focusView.requestFocus()
                showSoftInput(focusView)
            }
        }
        builder.show()
    }

    // 포커스를 주고 키보드를 올려주는 메서드
    fun showSoftInput(focusView: TextInputEditText){
        thread {
            SystemClock.sleep(1000)
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(focusView, 0)
        }
    }
}