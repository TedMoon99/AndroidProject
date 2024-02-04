package kr.co.lion.mini_project20240201

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import kr.co.lion.mini_project20240201.databinding.ActivityReportBinding

class activity_report : AppCompatActivity() {

    lateinit var activityReportBinding: ActivityReportBinding

    lateinit var activityEditLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityReportBinding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(activityReportBinding.root)

        setToolbar()
        setView()
        setEvent()

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
                setNavigationIcon(R.drawable.arrow_back_24px)
                // 뒤로가기 버튼 클릭 이벤트

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
        activityReportBinding.apply {
            toolbarReport.apply {
                setOnMenuItemClickListener {
                    when(it.itemId){
                        // 수정을 누른 경우 => activity_edit으로 화면을 바꾼다
                        R.id.menuItemReportEdit -> {
                            // Intent 객체 생성
                            val editIntent = Intent(this@activity_report, activity_edit::class.java)
                            // activity_edit 화면으로 전환
                            activityEditLauncher.launch(editIntent)
                        }

                        // 삭제를 누른 경우 => 해당 동물의 정보를 삭제하고 화면1로 돌아간다.
                        R.id.menuItemRegisterDelete -> {

                        }
                    }
                    true
                }

                // 뒤로가기 버튼을 누른 경우 => 그냥 나간다.
                setNavigationOnClickListener {
                    // 작업중인 것 취소
                    setResult(RESULT_CANCELED)
                    // 그냥 나간다
                    finish()
                }
            }
        }


    }

    }





