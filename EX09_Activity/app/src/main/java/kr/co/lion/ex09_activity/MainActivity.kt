import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex09_activity.InputActivity
import kr.co.lion.ex09_activity.ReportActivity
import kr.co.lion.ex09_activity.databinding.ActivityMainBinding
import kr.co.lion.ex09_activity.databinding.RowMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // InputActivity 실행을 위한 런처
    lateinit var inputActivityLauncher:ActivityResultLauncher<Intent>
    // ReportActivity 실행을 위한 런처
    lateinit var reportActivityLauncher:ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initData()
        initView()
        setEvent()
    }

    // 초기 데이터 셋팅
    fun initData(){
        // InputActivity 등록
        val contract1 = ActivityResultContracts.StartActivityForResult()
        inputActivityLauncher = registerForActivityResult(contract1){

        }

        // ReportActivity 등록
        val contract2 = ActivityResultContracts.StartActivityForResult()
        reportActivityLauncher = registerForActivityResult(contract2){

        }
    }
    // View 초기 셋팅
    fun initView(){
        activityMainBinding.apply {

            recyclerViewItem.apply {
                // RecyclerView에 어뎁터를 설정한다.
                adapter = RecyclerViewAdapter()
                // layoutManager 설정
                layoutManager = LinearLayoutManager(this@MainActivity)
                // 데코레이션
                val dec = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(dec)
            }
        }
    }
    // 이벤트 설정
    fun setEvent(){
        activityMainBinding.apply {
            // 학생 정보 입력 버튼 이벤트
            buttonInputInfo.setOnClickListener {
                // InputActivity를 실행한다.
                val newIntent = Intent(this@MainActivity, InputActivity::class.java)
                inputActivityLauncher.launch(newIntent)
            }
            // 총점 및 평균 버튼 이벤트
            buttonShowReport.setOnClickListener {
                // ReportActivity를 실행한다.
                val newIntent = Intent(this@MainActivity, ReportActivity::class.java)
                reportActivityLauncher.launch(newIntent)
            }
        }
    }

    // RecyclerView의 어뎁터
    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>(){

        // ViewHolder
        inner class ViewHolderClass(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            val rowMainBinding: RowMainBinding

            init{
                this.rowMainBinding = rowMainBinding

                // 항목 뷰의 가로 세로 길이 셋팅
                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowMainBinding)

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowMainBinding.textViewRowMainName.text = "홍길동 $position"
            holder.rowMainBinding.textViewRowMainGrade.text = "$position 학년"
        }
    }
}
