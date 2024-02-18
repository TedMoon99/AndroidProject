package kr.co.lion.mynotebook1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.mynotebook1.databinding.ActivityMainBinding
import kr.co.lion.mynotebook1.databinding.RowMainBinding
import org.jetbrains.annotations.Contract

// 목표 : 메모 어플 만들기 ( AppBar와 Calader View를 이용하여 Calander의 날짜를 클릭하면 그 날짜에 기록한 메모가 보여진다.)
class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var activityInputLauncher: ActivityResultLauncher<Intent> // InputActiviy를 위한 런처
    lateinit var activityShowLauncher: ActivityResultLauncher<Intent> // ShowActivity를 위한 런처

    val memoData = mutableListOf<MemoData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initData()
        setView()
        setEvent()
    }

    fun initData(){
        // Intent 사용을 위한 contract객체 생성

        // MainAcitivty -> InputActivity
        val contractInput = ActivityResultContracts.StartActivityForResult()
        activityInputLauncher = registerForActivityResult(contractInput){
            // 돌아온 뒤에 실행할 내용
            // InputActivity에서 작성한 내용을 받아와서 RecyclerView 항목에 넣어줘여 함
        }


        // MainActivity -> ShowActivity
        val contractShow = ActivityResultContracts.StartActivityForResult()
        activityShowLauncher = registerForActivityResult(contractShow){
            // 돌아온 뒤에 실행할 내용
            // ShowActivity의 내용을 그대로 다시 받아온다. (EditActivity에서 수정한 내용을 ShowActivity에 반영하는 형태로 작성할 것임)
        }
    }

    // View 설정
    fun setView(){
        activityMainBinding.apply {
            toolbarMain.apply {
                // 타이틀
                title = "Memo"
                // 메뉴
                inflateMenu(R.menu.main_menu)
            }

            // RecyclerView 항목들 구분선 생성
            recyclerViewMain.apply {
                val deco = MaterialDividerItemDecoration(context!!, LinearLayoutManager.VERTICAL)
                this.addItemDecoration(deco)
            }
        }


    }

    // 이벤트 처리
    fun setEvent(){
        activityMainBinding.apply {
            toolbarMain.apply {
                // 추가 메뉴 아이템 클릭시 InputActivity로 이동
                setOnMenuItemClickListener {
                    when (it.itemId){
                        R.id.menuItemAddMain -> {
                            // InputyActivity로 이동
                        }
                    }
                    true
                }
            }

            calandarViewMain.apply {
                // TODO("달력 항목 클릭시 RecyclerView에 보이는 항목들 필터링")
            }

            recyclerViewMain.apply {
                // 항목 클릭시 ShowActicity로 이동
                this.setOnClickListener {

                }
            }
        }

    }

    // RecyclerView 어댑터
    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>(){
        // ViewHolder
        inner class RecyclerViewHolder(rowMainBinding: RowMainBinding): RecyclerView.ViewHolder(rowMainBinding.root){
            val rowMainBinding : RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding

                // 항목 클릭시 전체가 선택되도록 만들기
                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val viewHolder = RecyclerViewHolder(rowMainBinding)
            return viewHolder
        }

        override fun getItemCount(): Int {
            return 0
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.rowMainBinding.textViewTitle.text = "제목 : ${memoData[position].title}"
            holder.rowMainBinding.textViewContent.text = "내용 : ${memoData[position].content}"
            holder.rowMainBinding.textViewDate.text = "작성 날짜 : ${memoData[position].date}"
        }

    }

}