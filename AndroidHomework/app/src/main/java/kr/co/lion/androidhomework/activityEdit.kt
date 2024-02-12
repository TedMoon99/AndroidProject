package kr.co.lion.androidhomework

import android.content.Intent
import android.os.Build
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
                val memoData = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    intent.getParcelableExtra("intentEditStart", MemoData::class.java)
                } else {
                    intent.getParcelableExtra<MemoData>("intentEditStart")
                }
                setText(memoData?.title.toString())

            }
            textFieldEditContent.apply {
                // intent에 담겨서 전달된 데이터를 가져온다.
                val memoData = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    intent.getParcelableExtra("intentEditStart", MemoData::class.java)
                } else {
                    intent.getParcelableExtra<MemoData>("intentEditStart")
                }

                // text에 넣어준다.
                setText(memoData?.content.toString())
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
                            val memoData = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                                intent.getParcelableExtra("intentEditStart", MemoData::class.java)
                            } else {
                                intent.getParcelableExtra<MemoData>("intentEditStart")
                            }

                            // 수정된 항목 받아오기
                            val editTitleDone = textFieldEditTitle.text.toString()
                            val editContentDone = textFieldEditContent.text.toString()
                            val editMemoData = memoData?.date.toString()
                            // intent에 수정된 내용 전달
                            val intentEditDone = Intent(this@activityEdit, activityShow::class.java)

                            // 객체로 저장
                            val edittedMemoData = MemoData(editTitleDone, editContentDone, editMemoData)

                            // intent에 객체 전달
                            intentEditDone.putExtra("intentEditDone", edittedMemoData)
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