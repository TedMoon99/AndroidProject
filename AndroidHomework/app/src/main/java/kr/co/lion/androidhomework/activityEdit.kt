package kr.co.lion.androidhomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.androidhomework.databinding.ActivityEditBinding

class activityEdit : AppCompatActivity() {

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
        activityEditBinding.apply {
            toolbarEdit.apply {
                // 타이틀
                title = "메모 수정"
                // 메뉴
                inflateMenu(R.menu.menu_edit)

                // 내비게이션 아이콘 설정
                setNavigationIcon(R.drawable.arrow_back_24px)
            }

            textFieldEditTitle.apply {
                // intent에 담겨서 전달된 데이터를 가져온다
                val titleE = intent?.getStringExtra("title")

                // text에 넣어준다.
                setText(titleE)
            }
            textFieldEditContent.apply {
                // intent에 담겨서 전달된 데이터를 가져온다.
                val contentE = intent?.getStringExtra("content")

                // text에 넣어준다.
                setText(contentE)
            }
        }
    }

    // Event 설정
    fun setEvent(){
        activityEditBinding.apply {
            toolbarEdit.apply {
                // 메뉴 아이템 리스너
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menuItemEdit -> {

                            // 수정된 항목 받아오기
                            val editTitleDone = textFieldEditTitle.text
                            val editContentDone = textFieldEditContent.text

                            // intent에 수정된 내용 전달
                            val intentEditDone = Intent(this@activityEdit, activityShow::class.java)
                            intentEditDone.putExtra("editTitleDone", editTitleDone)
                            intentEditDone.putExtra("editContentDone", editContentDone)

                            // 작업의 결과를 ativity_show로 전달해주기
                            setResult(RESULT_OK, intentEditDone)

                            // activity_show 시작
                            startActivity(intentEditDone)

                            // 현재 activity 파괴
                            finish()
                        }
                    }
                    true
                }
                // 내비게이션 리스너
                setNavigationOnClickListener {
                    // 작성 내용 파기 후 복귀
                    setResult(RESULT_CANCELED)
                    finish()
                }
            }
        }
    }
}