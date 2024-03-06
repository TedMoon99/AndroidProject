package kr.co.lion.mynotebook1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.mynotebook1.databinding.ActivityInputBinding
import java.text.SimpleDateFormat
import java.util.Date

class InputActivity : AppCompatActivity() {

    lateinit var activityInputBinding: ActivityInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityInputBinding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(activityInputBinding.root)

        setView()
        setEvent()
    }

    // View 설정
    fun setView(){
        activityInputBinding.apply {
            toolbarInput.apply {
                // title
                title = "Memo Input"
                // menu
                inflateMenu(R.menu.menu_input)
                // NavigationIcon
                setNavigationIcon(R.drawable.arrow_back_24px)
            }
        }
    }
    // Event 설정
    fun setEvent(){
        activityInputBinding.apply {
            // toolbar 리스너
            toolbarInput.apply {
                // Navigation Icon 리스너
                setNavigationOnClickListener {
                    // 뒤로가기
                    setResult(RESULT_CANCELED)
                    finish()
                }
                // menuItem 리스너
                setOnMenuItemClickListener {
                    when (it.itemId){
                        R.id.menuItemInputDone -> { // 완료
                            // Intent 객체 생성
                            val intentInputDone = Intent(this@InputActivity, MainActivity::class.java)

                            // 입력 받기
                            val title = textFieldTitle.text.toString()
                            val content = textFieldContent.text.toString()

                            val temp = Date()
                            val formatted = SimpleDateFormat("yyyy-MM-dd")
                            val date = formatted.format(temp)
                            // memo 객체 생성
                            val memo = MemoData(title, content, date.toString())
                            intentInputDone.putExtra("InputDone",memo)
                            setResult(RESULT_OK, intentInputDone)
                            // 파괴
                            finish()
                        }
                    }
                    true
                }

            }
        }
    }
}