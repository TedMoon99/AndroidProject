package kr.co.lion.androidhomework

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDivider
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.androidhomework.databinding.ActivityMainBinding
import kr.co.lion.androidhomework.databinding.RowMainBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDate.now
import java.util.Date

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    // activityInput 런처
    lateinit var activityInputLauncher: ActivityResultLauncher<Intent>

    val memoList = mutableListOf<MemoData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initData()
        setView()
        setEvent()
    }

    // 초기 데이터 설정
    fun initData(){

        activityMainBinding.apply {
            // 어댑터 객체 생성
            val recyclerViewAdapter = RecyclerViewAdapter()

            // 어댑터를 적용해준다.
            recyclerViewMain.adapter = recyclerViewAdapter

            recyclerViewMain.layoutManager = LinearLayoutManager(this@MainActivity)

            val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
            recyclerViewMain.addItemDecoration(deco)


        }

    }

    // View 설정
    fun setView(){
        activityMainBinding.apply {
            // toolbar 설정
            toolbarMain.apply {
                // 타이틀
                title = "메모 관리"
                // 메뉴
                inflateMenu(R.menu.menu_main)
            }

            //


        }
    }
    // Event 설정
    fun setEvent(){
        activityMainBinding.apply {
            // 툴바 이벤트 설정
            toolbarMain.apply {
                // 툴바 +버튼 클릭시 activityInput으로 이동
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menuitemMain -> {
                            // activityInput 실행
                            val requestLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
                                ActivityResultContracts.StartActivityForResult()){
                                 val resultData = it.data?.getStringExtra("result")
                                activityMainBinding.recyclerViewMain.adapter.
                            }
                        }
                    }
                    true
                }
            }
        }

    }

    // RecyclerView의 어댑터 : ViewHolder에 있는 뷰 객체에 적절한 데이터를 대입
    inner class  RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderMain>(){
        // ViewHolder : 각 항목을 구성하는 뷰 객체
        inner class ViewHolderMain(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            // 매개변수로 들어오는 바인딩객체를 담을 프로퍼티
            val rowMainBinding : RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding

                // 항목 클릭시 전체가 클릭될 수 있또록
                // 가로, 세로의 길이를 설정해준다.
                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, // 가로 길이 설정 => 부모 레이아웃에 맞춤
                    ViewGroup.LayoutParams.WRAP_CONTENT // 세로 길이 설정 => 내용에 맞춤
                )

                // 항목 클릭시 리스너 설정
                this.rowMainBinding.root.setOnClickListener {
                    // activityShow 화면으로 이동

                }
            }

        }

        // onCreateViewHolder() : 항목의 뷰를 가지는 ViewHolder를 준비하려고 자동으로 호출
        // => 항목을 구성할 때 이용할 ViewHolder 객체를 준비한다.
        // => ViewBinding 기법으로 ViewHolder 객체를 생성해 반환하는 구문
        // => 이 함수에서 반환한 ViewHolder 객체는 자동으로 onBindViewHolder() 함수의 매개변수로 전달된다.
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val viewHolderMain = ViewHolderMain(rowMainBinding)

            // onCreateViewHolder() 함수에서 onViewBinding 함수에 매개변수로 전달해준다.
            return viewHolderMain
        }

        // getItemCount() : 항목 개수를 판단하려고 자동으로 호출됩니다
        // => 이 함수가 반환한 숫자만큼 onBindViewHolder() 함수가 호출되어 항목을 만든다
        override fun getItemCount(): Int {
            return memoList.size
        }

        // onBindViewHolder() : ViewHolder의 뷰에 데이터를 출력하려고 자동으로 호출됩니다.
        // =>

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
            // 날짜는 현재 날짜를 구해서 사용한다.
            // => LocalDate.now()는 API 26부터 지원한다. 현재 이 프로젝트의 최소 API가 24이므로 분기가 필요함
            /*
            API 26이상 => LocalDate 클래스의 now() 이용가능
            API 26미만 => java.util 클래스의 Date() 이용가능 ( 별도의 형식 포맷이 필요함 )
            */
            holder.rowMainBinding.textViewTitle.text = "제목 : ${memoList[position].title}"
            holder.rowMainBinding.textViewContent.text = "내용 : ${memoList[position].content}"

            // Android 8.0 (Oreo) 이상에서 동작하는 코드
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val currentDate = LocalDate.now()

                // 작성 날짜도 ViewHolder 안에 넣어주자
                holder.rowMainBinding.textViewDate.text = "작성 날짜 : ${currentDate}"

            }
            // Android 8.0 (Oreo) 미만에서 동작하는 코드
            else {
                // 현재 날짜와 시간 가져오기
                val currentDate = Date()
                val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                val formattedDate: String = dateFormat.format(currentDate)

                // 작성 날짜도 ViewHolder 안에 넣어주자 => dataClass 생성
                holder.rowMainBinding.textViewDate.text = "작성 날짜 : ${formattedDate}"
            }
        }
    }


}