package kr.co.lion.androidhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.androidhomework.databinding.ActivityInputBinding

class activityInput : AppCompatActivity() {

    lateinit var activityInputBinding: ActivityInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityInputBinding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(activityInputBinding.root)

        setView()
        setEvent()


    }

    // View 설정
    fun setView(){
        activityInputBinding.apply {
            toolbarInput.apply {
                // 타이틀
                title = "메모 작성"
                // 메뉴
                inflateMenu(R.menu.menu_input)

                // 내비게이션 아이콘
                setNavigationIcon(R.drawable.arrow_back_24px)
            }
        }
    }

    // Event 설정
    fun setEvent(){
        activityInputBinding.apply {
            toolbarInput.apply {

                // 내비게이션 아이콘 클릭 => 작성 중이던 내용 파기후 뒤로가기
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                // 메뉴 아이템 클릭 => 작성된 내용을 recyclerView에 전달하기
                setOnMenuItemClickListener {
                    when (it.itemId){
                        R.id.menuItemDone -> {
                            setResult(RESULT_OK)
                            // TODO: 작성된 내용을 recyclerView에 전달하기
                            val title = textFieldTitle.text.toString()
                            val content = textFieldContent.text.toString()

                            finish()
                        }
                    }
                    true
                }
            }
        }
    }


}