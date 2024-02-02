package kr.co.lion.mini_project20240201

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.mini_project20240201.databinding.ActivityRegisterBinding
import kr.co.lion.mini_project20240201.databinding.ActivityReportBinding

class activity_report : AppCompatActivity() {

    lateinit var activityReportBinding: ActivityReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityReportBinding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(activityReportBinding.root)
    }

    // 툴바 설정
    fun setToolbar(){
        activityReportBinding.apply {
            toolbarReport.apply {
                // 타이틀
                title = "동물 정보"
                // 메뉴
                inflateMenu(R.menu.menu_report)

                // 뒤로가기 버튼
                // TODO: 뒤로가기 버튼 만들기

            }
        }

    }

    // View 설정

    // 동물의 정보를 출력한다.
    fun setView(){
        // 제일 위에 동물의 정보를 출력하고 각 동물에 맞는 정보를 출력한다.
    }

    // 이벤트 설정
    fun setEvent(){
        // 수정을 누른 경우 => activity_edit으로 화면을 바꾼다

        // 삭제를 누른 경우 => 해당 동물의 정보를 삭제하고 화면1로 돌아간다.

        // 뒤로가기 버튼을 누른 경우 => 그냥 나간다.

    }





}