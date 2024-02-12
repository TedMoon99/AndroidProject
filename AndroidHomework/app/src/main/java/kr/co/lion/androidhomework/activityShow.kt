package kr.co.lion.androidhomework

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.androidhomework.databinding.ActivityShowBinding

class activityShow : AppCompatActivity() {

    lateinit var activityShowBinding: ActivityShowBinding

    lateinit var activityEditLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityShowBinding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(activityShowBinding.root)


        initData()
        setView()
        setEvent()
    }

    // 기초 데이터 설정
    fun initData(){
        // activityEdit 런처
        val contract = ActivityResultContracts.StartActivityForResult()
        activityEditLauncher = registerForActivityResult(contract){
            // 작업결과가 OK라면
            if (it.resultCode == RESULT_OK){
                // 전달된 Intent 객체가 있다면
                if (it.data != null){
                    // 객체를 추출한다.
                    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU){
                        val memoData = it.data?.getParcelableExtra("intentEditDone",MemoData::class.java)
                        // 편집 후의 내용으로 갱신한다.
                        activityShowBinding.textViewShowTitle.setText(memoData?.title.toString())
                        activityShowBinding.textViewShowContent.setText(memoData?.title.toString())
                    } else {
                        val memoData = it.data?.getParcelableExtra<MemoData>("intentEditDone")
                        // 편집 후의 내용으로 갱신한다.
                        activityShowBinding.textViewShowTitle.setText(memoData?.title.toString())
                        activityShowBinding.textViewShowContent.setText(memoData?.title.toString())
                    }
                }
            } else {
                activityShowBinding.textViewShowTitle.setText("헤헤 실패")
                activityShowBinding.textViewShowContent.setText("헤헤 실패")
                activityShowBinding.textViewShowDate.setText("헤헤 실패")
            }
        }
    }

    // View 설정
    fun setView(){
        activityShowBinding.apply {
            // 툴바 설정
            toolbarShow.apply {
                // 타이틀
                title = "메모 보기"
                // 메뉴 설정
                inflateMenu(R.menu.menu_show)
                // 내비게이션 설정
                setNavigationIcon(R.drawable.arrow_back_24px)
            }
            textViewShowTitle.apply {
                // Intent로 부터 학생 정보 객체를 추출한다.
                val memoData = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    intent.getParcelableExtra("intentShow1", MemoData::class.java)
                } else {
                    intent.getParcelableExtra<MemoData>("intentShow1")
                }
                text = "제목 : ${memoData?.title}"
            }
            textViewShowContent.apply {
                // Intent로 부터 학생 정보 객체를 추출한다.
                val memoData = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    intent.getParcelableExtra("intentShow1", MemoData::class.java)
                } else {
                    intent.getParcelableExtra<MemoData>("intentShow1")
                }
                text = "내용 : ${memoData?.content}"
            }
            textViewShowDate.apply {
                // Intent로 부터 학생 정보 객체를 추출한다.
                val memoData = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    intent.getParcelableExtra("intentShow1", MemoData::class.java)
                } else {
                    intent.getParcelableExtra<MemoData>("intentShow1")
                }
                text = "작성 날짜 : ${memoData?.date}"
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
                    // 편집내용 전달 후 종료
                    val intentEditted = Intent("EditDone", )
                    setResult(RESULT_OK)

                    finish()
                }
                // 메뉴아이템 리스너 설정
                setOnMenuItemClickListener {
                    when (it.itemId){
                        // 수정
                        R.id.menuItemEdit -> {
                            // activity_edit 화면으로 이동
                            val intentEdit = Intent(this@activityShow, activityEdit::class.java)
                            val memoData = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                                intent.getParcelableExtra("intentShow1", MemoData::class.java)
                            } else {
                                intent.getParcelableExtra<MemoData>("intentShow1")
                            }
                            intentEdit.putExtra("intentEditStart",memoData)
                            activityEditLauncher.launch(intentEdit)
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