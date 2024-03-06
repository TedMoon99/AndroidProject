package kr.co.lion.mynotebook1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.mynotebook1.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {

    lateinit var activityShowBinding: ActivityShowBinding
    lateinit var activityEditLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityShowBinding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(activityShowBinding.root)
    }

    // 기초 데이터 설정
    fun initData(){
        val contractEdit = ActivityResultContracts.StartActivityForResult()
        activityEditLauncher = registerForActivityResult(contractEdit){
            // EditActivity에서 돌아왔을 때 실행할 요청사항
        }

    }

    // View 설정
    fun setView(){
        activityShowBinding.apply {
            toolbarShow.apply {
                // title
                title = "Memo Show"
                // menu
                inflateMenu(R.menu.menu_show)
                // Navigation Icon
                setNavigationIcon(R.drawable.arrow_back_24px)
            }

            // 항목들 intent로 받아와서 보여주기

        }
    }

    // Event 설정
    fun setEvent(){
        activityShowBinding.apply {
            toolbarShow.apply {
                setOnMenuItemClickListener {
                    when (it.itemId){
                        R.id.menuItemEdit -> { // Edt
                            val intentEdit = Intent(this@ShowActivity, EditActivity::class.java)
                        }
                        R.id.menuItemDelete -> { // Delete

                        }
                    }
                    true
                }

            }
        }
    }
}