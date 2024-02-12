package kr.co.lion.androidhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.androidhomework.databinding.ActivityShowBinding

class activityShow : AppCompatActivity() {

    lateinit var activityShowBinding: ActivityShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityShowBinding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(activityShowBinding.root)


    }

    // View 설정
    fun setView(){
        activityShowBinding.apply {
            // 툴바 설정
            toolbarShow.apply {
                // 타이틀
                title = "메모 보기"
                // 메뉴 설정

                // 내비게이션 설정
                setNavigationIcon(R.drawable.arrow_back_24px)

            }
        }
    }

    // Event 설정
    fun setEvent(){
        activityShowBinding.apply {
            // 툴바 리스너 설정
            toolbarShow.apply {
                // 내비게이션 리스너 설정
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
                // 메뉴아이템 리스너 설정
                setOnMenuItemClickListener {
                    when (it.itemId){
                        // 수정
                        R.id.menuItemEdit -> {
                            // activity_edit 화면으로 이동
                        }

                        // 삭제
                        R.id.menuItemDelete -> {
                            // 항목 삭제

                        }
                    }
                    true
                }
            }
        }
    }
}