package kr.co.lion.androidhomework

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.androidhomework.databinding.ActivityMainBinding
import kr.co.lion.androidhomework.databinding.RowMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var activityInputLauncher: ActivityResultLauncher<Intent>
    lateinit var activityShowLauncher: ActivityResultLauncher<Intent>

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
    @SuppressLint("NotifyDataSetChanged")
    fun initData(){
        // InputActivity 런처
        val contract1 = ActivityResultContracts.StartActivityForResult()
        activityInputLauncher = registerForActivityResult(contract1){
            // 작업 결과가 OK 라면
            if(it.resultCode == RESULT_OK){
                // 전달된 Intent객체가 있다면
                if(it.data != null){
                    // 객체를 추출한다.
                    if(Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU) {
                        val memoData = it.data?.getParcelableExtra("intentInputDone", MemoData::class.java)
                        memoList.add(memoData!!)
                        activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                    } else {
                        val memoData = it.data?.getParcelableExtra<MemoData>("intentInputDone")
                        memoList.add(memoData!!)
                        activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                    }
                }
            }
        }

        // ShowInfoActivity 런처
        val contract2 = ActivityResultContracts.StartActivityForResult()
        activityShowLauncher = registerForActivityResult(contract2){
            if (it.resultCode == RESULT_OK){
                if(it.data != null){
                    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU){
                        val memoData = it.data?.getParcelableExtra("EditDone", MemoData::class.java)
                        memoList.add(memoData!!)
                        activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                    } else{
                        val memoData = it.data?.getParcelableExtra<MemoData>("EditDone")

                    }
                }
            }
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
            recyclerViewMain.apply {
                // 어댑터 객체 생성
                val recyclerViewAdapter = RecyclerViewAdapter()

                // 어댑터를 적용해준다. <=> RecyclerView 출력
                recyclerViewMain.adapter = recyclerViewAdapter
                recyclerViewMain.layoutManager = LinearLayoutManager(this@MainActivity)

                val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                recyclerViewMain.addItemDecoration(deco)
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
                            activityInputLauncher.launch(intentInput)
                        }
                    }
                    true
                }
            }
        }
    }
    inner class  RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderMain>(){
        @SuppressLint("NotifyDataSetChanged")
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

                    // 선택된 항목의 객체를 담아준다
                    intentShow1.putExtra("intentShow1", memoList[adapterPosition])

                    // activity_show 화면으로 이동
                    activityShowLauncher.launch(intentShow1)
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


        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
            holder.rowMainBinding.textViewTitle.text = "제목 : ${memoList[position].title}"
            holder.rowMainBinding.textViewContent.text = "내용 : ${memoList[position].content}"
            holder.rowMainBinding.textViewDate.text = "작성 날짜 : ${memoList[position].date}"
        }
    }
}