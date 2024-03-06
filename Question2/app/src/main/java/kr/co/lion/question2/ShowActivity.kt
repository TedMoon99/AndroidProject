package kr.co.lion.question2

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.question2.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {
    lateinit var activityShowBinding: ActivityShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityShowBinding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(activityShowBinding.root)

        setView()
        setEvent()
    }

    // View
    fun setView(){
        activityShowBinding.apply {
            // intent 객체를 받아와서 적용한다.
            val dogData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                intent.getParcelableExtra("DogData", DogInfo::class.java)
            } else  {
                intent.getParcelableExtra<DogInfo>("DogData")
            }
            // 이름 적용
            textViewShowName.setText("이름 : ${dogData?.name}")
            // 견종 적용
            textViewShowType.setText("견종 : ${dogData?.type}")
            // 나이 적용
            textViewShowAge.setText("나이 : ${dogData?.age}")


            toolbarShow.apply {
                // title
                title = "Dog Info Show"
                // menu
                inflateMenu(R.menu.menu_show)
                // Navigation
                setNavigationIcon(R.drawable.done_24px)
            }
        }
    }

    // Event
    fun setEvent(){
        activityShowBinding.apply {
            toolbarShow.apply {
                setNavigationOnClickListener {
                    // Done
                    finish()
                }
            }
        }
    }
}