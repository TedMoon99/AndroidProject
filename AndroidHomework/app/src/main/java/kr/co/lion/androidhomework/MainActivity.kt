package kr.co.lion.androidhomework

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Input
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.ActivityResult
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

    val memoList = mutableListOf<MemoData>()

    val contract = ActivityResultContracts.StartActivityForResult()

    // activity_input 화면 호출시 사용할 런처
    val getResultText1 = registerForActivityResult(contract){
        if (it != null){
            when (it.resultCode){
                RESULT_OK -> {
                    if (it.data != null){
                        val titleI = it.data!!.getStringExtra("inputTitleDone")
                        val contentI = it.data!!.getStringExtra("inputContentDone")
                        val date = Date()
                    }
                }
            }
        }
    }

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

            // 어댑터를 적용해준다. <=> RecyclerView 출력
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
                            val intentInput = Intent(this@MainActivity, activityInput::class.java)
                            getResultText1.launch(intentInput)
                        }
                    }
                    true
                }
            }
        }

    }

    // RecyclerView의 어댑터 : ViewHolder에 있는 뷰 객체에 적절한 데이터를 대입
    // => ViewHolder의 View에 데이터를 출력해 각 항목을 만들어 주는 역할
    inner class  RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderMain>(){
        @SuppressLint("NotifyDataSetChanged")
        val gerResultText2 = registerForActivityResult(contract){
            if (it != null){
                when (it.resultCode){
                    RESULT_OK -> {
                        if ( it.data != null){
                            val titleE = it.data!!.getStringExtra("editTitleDone1")
                            val ContentE = it.data!!.getStringExtra("editTitleDone1")

                            notifyDataSetChanged()
                        }
                    }
                }
            }
        }
        // ViewHolder : 각 항목을 구성하는 뷰 객체
        inner class ViewHolderMain(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            // 매개변수로 들어오는 바인딩객체를 담을 프로퍼티
            val rowMainBinding : RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding

                // 항목 클릭시 전체가 클릭될 수 있도록
                // 가로, 세로의 길이를 설정해준다.
                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, // 가로 길이 설정 => 부모 레이아웃에 맞춤
                    ViewGroup.LayoutParams.WRAP_CONTENT // 세로 길이 설정 => 내용에 맞춤
                )

                // 항목 클릭시 반응하는 리스너
                this.rowMainBinding.root.setOnClickListener {
                    // intent 객체 생성
                    val intentShow1 = Intent(this@MainActivity, activityShow::class.java)
                    // 현재 항목에 있는 정보를 intent에 저장
                    val title = this.rowMainBinding.textViewTitle.text
                    val content = this.rowMainBinding.textViewContent.text
                    val date = this.rowMainBinding.textViewDate.text

                    intentShow1.putExtra("titleMain", title)
                    intentShow1.putExtra("contentMain", content)
                    intentShow1.putExtra("dateMain", date)

                    // activity_show 화면으로 이동
                    gerResultText2.launch(intentShow1)
                }
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val viewHolderMain = ViewHolderMain(rowMainBinding)
            return viewHolderMain
        }
        override fun getItemCount(): Int {
            return memoList.size
        }

            @SuppressLint("SetTextI18n", "SimpleDateFormat")
        override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
            holder.rowMainBinding.textViewTitle.text = "제목 : ${memoList[position].title}"
            holder.rowMainBinding.textViewContent.text = "내용 : ${memoList[position].content}"

            // Android 8.0 (Oreo) 이상에서 동작하는 코드
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val currentDate = LocalDate.now()

                // 작성 날짜도 ViewHolder 안에 넣어주자
                holder.rowMainBinding.textViewDate.text = "작성 날짜 : ${currentDate}"
            } // Android 8.0 (Oreo) 미만에서 동작하는 코드
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