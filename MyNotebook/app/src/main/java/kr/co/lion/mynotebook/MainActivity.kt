package kr.co.lion.mynotebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.mynotebook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setView()

    }

    // View 설정
    fun setView(){
        activityMainBinding.apply {
            // 메뉴
            toolbarTest.apply {
                // 타이틀
                title = "Toolbar in Appbar"
                // 메뉴
                inflateMenu(R.menu.menu_toolbar_in_appbar)
            }
            this.textView.text = "이 쪽부터는 Appbar Layout의 영역이 아닙니다!"

        }
    }
}