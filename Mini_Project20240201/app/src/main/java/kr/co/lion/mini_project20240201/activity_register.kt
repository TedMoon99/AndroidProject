package kr.co.lion.mini_project20240201

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kr.co.lion.mini_project20240201.databinding.ActivityMainBinding
import kr.co.lion.mini_project20240201.databinding.ActivityRegisterBinding

class activity_register : AppCompatActivity() {

    lateinit var activityRegisterBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityRegisterBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(activityRegisterBinding.root)


    }

    // 툴바 설정
    fun setToolbar(){
        activityRegisterBinding.apply {
            toolbarRegister.apply {
                // 타이틀
                title = "동물 등록"
                // 메뉴
                inflateMenu(R.menu.menu_register)

                // 뒤로가기 버튼
                // TODO: 뒤로가기 버튼 만들기

            }
        }

    }


    // View 설정
    fun setView(){

        activityRegisterBinding.apply {
            // 사자 버튼
            imagebuttonLion.setOnClickListener{
                // 화면에 등장하게 설정
                nameInput.visibility = View.VISIBLE
                ageInput.visibility = View.VISIBLE
                detailInput1.visibility = View.VISIBLE
                detailInput2.visibility = View.VISIBLE

                // 세부사항을 사자에 맞게 설정
                detailInputLayout1.hint = "털의 개수"
                detailInput1.inputType = android.text.InputType.TYPE_CLASS_NUMBER

                detailInputLayout2.hint = "성별(암컷 또는 수컷)"
                detailInput2.inputType = android.text.InputType.TYPE_CLASS_TEXT
            }

            // 호랭이 버튼
            imageButtonTiger.setOnClickListener{
                // 화면에 등장하게 설정
                nameInput.visibility = View.VISIBLE
                ageInput.visibility = View.VISIBLE
                detailInput1.visibility = View.VISIBLE
                detailInput2.visibility = View.VISIBLE

                // 세부사항을 사자에 맞게 설정
                detailInputLayout1.hint = "쥴무늬 개수"
                detailInput1.inputType = android.text.InputType.TYPE_CLASS_NUMBER

                detailInputLayout2.hint = "몸무게(50kg ~ 200kg)"
                detailInput2.inputType = android.text.InputType.TYPE_CLASS_NUMBER
            }
            // 기린 버튼
            imagebuttonLion.setOnClickListener{
                // 화면에 등장하게 설정
                nameInput.visibility = View.VISIBLE
                ageInput.visibility = View.VISIBLE
                detailInput1.visibility = View.VISIBLE
                detailInput2.visibility = View.VISIBLE

                // 세부사항을 사자에 맞게 설정
                detailInputLayout1.hint = "목의 길이"
                detailInput1.inputType = android.text.InputType.TYPE_CLASS_NUMBER

                detailInputLayout2.hint = "달리는 속도(자유 입력)"
                detailInput2.inputType = android.text.InputType.TYPE_CLASS_TEXT
            }
        }

        // 이벤트 설정
        fun setEvent(){
            activityRegisterBinding.apply {
                toolbarRegister.setOnMenuItemClickListener {
                    // 완료 버튼을 누르면 화면 1로 이동하고 화면 1의 RecyclerView의 목록에 나오도록 한다.


                    true
                }
            }
        }
    }






}