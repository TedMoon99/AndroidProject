package kr.co.lion.androidhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.androidhomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        initData()
        setView()
        setEvent()
    }

    // 초기 데이터 설정
    fun initData(){

    }

    // View 설정
    fun setView(){
        activityMainBinding.apply {
            toolbarMain.apply {
                // 타이틀
                title = "메모 관리"
                // 메뉴
                inflateMenu(R.menu.menu_main)

            }

        }
    }
    // Event 설정
    fun setEvent(){

    }

}