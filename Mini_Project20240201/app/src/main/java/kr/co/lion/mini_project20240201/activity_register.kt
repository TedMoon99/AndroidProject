package kr.co.lion.mini_project20240201

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar
import kr.co.lion.mini_project20240201.databinding.ActivityRegisterBinding

class activity_register : AppCompatActivity() {

    lateinit var activityRegisterBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityRegisterBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(activityRegisterBinding.root)

        setView()
        setEvent()
    }

    // 툴바 설정


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
                detailInputLayout1.hint = "줄무늬 개수"
                detailInput1.inputType = android.text.InputType.TYPE_CLASS_NUMBER

                detailInputLayout2.hint = "몸무게(50kg ~ 200kg)"
                detailInput2.inputType = android.text.InputType.TYPE_CLASS_NUMBER
            }
            // 기린 버튼
            imageButtonGiraffe.setOnClickListener{
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

            // 뒤로가기 버튼 설정
            toolbarRegister.apply {
                // 타이틀
                title = "동물 등록"
                inflateMenu(R.menu.menu_register)
                // 뒤로가기 버튼 설정
                setNavigationIcon(R.drawable.arrow_back_24px)
            }
        }
    }
    // 이벤트 설정
    fun setEvent(){
        activityRegisterBinding.apply {
            toolbarRegister.setOnMenuItemClickListener {
                when (it.itemId) {
                    // 완료 버튼을 누르면 화면 1로 이동하고 화면 1의 RecyclerView의 목록에 나오도록 한다.
                    R.id.menuItemDone ->  {
                        // 입력된 항목들 받아오기
                        processInputDone()
                        // RecyclerView의 목록에 나오도록 한다.

                        // 화면 1로 이동
                        finish()
                    }
                }
                true
            }
            toolbarRegister.apply {
                // 뒤로가기 버튼을 클릭할 경우 작성중이던 것을 취소하고 이전의 화면으로 넘어간다.
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
            }
        }
    }

    fun processInputDone(){
        activityRegisterBinding.apply {
            // 사용자가 입력한 항목을 받아온다.
            val name = nameInput.text.toString()
            val age = ageInput.text.toString()
            val detail1 = detailInput1.text.toString()
            val detail2 = detailInput2.text.toString()

            // 입력 유효성 검사
            if (name.isEmpty()){
                // TODO: 다이얼로그 출력
                return
            }
            if (age.isEmpty()){
                // TODO: 다이얼로그 출력
                return
            }
            if (detail1.isEmpty()){
                // TODO: 다이얼로그 출력
                return
            }
            if (detail2.isEmpty()){
                // TODO: 다이얼로그 출력
                return
            }

            // 입력받은 정보를 객체에 담아준다.
            val animalData = AnimalData(name, age, detail1, detail2)

            Snackbar.make(activityRegisterBinding.root,"등록이완료되었습니다.", Snackbar.LENGTH_SHORT).show()

            // 이전으로 돌아간다.
            val resultIntent = Intent()
            resultIntent.putExtra("AnimalData",animalData)
            setResult(RESULT_OK,resultIntent)
            finish()

        }
    }

}