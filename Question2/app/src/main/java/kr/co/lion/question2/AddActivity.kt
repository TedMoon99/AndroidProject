package kr.co.lion.question2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.question2.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    lateinit var activityAddBinding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityAddBinding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(activityAddBinding.root)

        setView()
        setEvent()
    }

    // Veiw 설정
    fun setView(){
        activityAddBinding.apply {
            toolbarAdd.apply {
                // title
                title = "Dog Info Add"
                // menu
                inflateMenu(R.menu.menu_add)

                // Navigation
                setNavigationIcon(R.drawable.arrow_back_24px)
            }
        }

    }

    // Event 설정
    fun setEvent(){
        activityAddBinding.apply {
            toolbarAdd.apply {
                // 완료 버튼 클릭
                setOnMenuItemClickListener {
                    when (it.itemId){
                        R.id.menuItemAddDone -> {
                            val dogName = textFieldInputName.text.toString()
                            val dogType = textFieldInputName.text.toString()
                            val dogAge = textFieldInputAge.text.toString().toInt()

                            val inputResult = DogInfo(dogName, dogType, dogAge)

                            // 정보를 저장하여 intent로 전달
                            val intentAddDone = Intent()
                            intentAddDone.putExtra("InputDone", inputResult)
                            setResult(RESULT_OK, intentAddDone)
                            finish() // 파기
                        }
                    }
                    true
                }
                // 뒤로가기 클릭
                setNavigationOnClickListener {
                    // 정보를 저장하지 않고 파기
                    setResult(RESULT_CANCELED)
                    finish()
                }

            }
        }

    }
}