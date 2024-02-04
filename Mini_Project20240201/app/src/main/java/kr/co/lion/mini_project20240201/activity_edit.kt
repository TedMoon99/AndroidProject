package kr.co.lion.mini_project20240201

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.mini_project20240201.databinding.ActivityEditBinding

class activity_edit : AppCompatActivity() {

    lateinit var activityEditBinding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityEditBinding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(activityEditBinding.root)

        setView()
        setEvent()
    }


    // View 설정
    fun setView(){
        // 툴바 설정
        activityEditBinding.apply {
            toolbarEdit.apply {
                // 타이틀
                title = "동물 정보 수정"

                // 메뉴
                inflateMenu(R.menu.menu_edit)

                // 뒤로가기
                setNavigationIcon(R.drawable.arrow_back_24px)

            }
        }

        // 수정하고자 하는 동물의 정보를 출력

    }

    // 이벤트 설정
    fun setEvent(){
        // 완료 버튼을 누르면 수정을 완료하고 activity_report화면으로 돌아간다.

        // 뒤로가기 버튼 클릭시 실행 취소하고 뒤로 돌아간다.
        activityEditBinding.apply {
            toolbarEdit.setNavigationOnClickListener {
                setResult(RESULT_CANCELED)
                finish()
            }
        }

    }



}