package kr.co.lion.question2

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.question2.databinding.ActivityMainBinding
import kr.co.lion.question2.databinding.RowMainBinding

/*
문제2)

강아지 정보를 관리하는 어플을 만든다.

첫 화면에는 버튼과 리사이클러뷰가 있다.
첫 화면의 버튼을 누르면 입력화면이 나타난다.

입력 화면에서는 강아지의 이름, 견종, 나이를 입력받는다.
견종은 몰티즈, 비숑, 푸들 이렇게 3가지 이다.

첫 화면의 리사이클러뷰의 항목을 누르면
강아지 정보를 보는 화면이 나타난다.
강아지 정보를 보는 화면에는 선택한 강아지의 정보를 출력한다.

데이터는 저장하지 않는다.

모든 화면은 Activity로 구성한다.

 */
class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var addActivityLauncher: ActivityResultLauncher<Intent>

    val dogInfoList = mutableListOf<DogInfo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        setView()
        initData()
        setEvent()
    }

    fun setView(){
        activityMainBinding.apply {
            // RecyclerView
            recyclerViewMain.apply {
                // Adapter
                adapter = RecyclerViewMainAdapter()
                // LayoutManager
                layoutManager = LinearLayoutManager(this@MainActivity)
                // Line
                val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    fun initData(){
        val contract  = ActivityResultContracts.StartActivityForResult()
        addActivityLauncher = registerForActivityResult(contract){
            // 작업결과가 OK
            if (it.resultCode != RESULT_CANCELED){
                // 전달된 Intent 객체 존재
                if (it.data != null){
                    // 객체를 추루하자
                    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU){
                        val dogData = it.data?.getParcelableExtra("InputDone", DogInfo::class.java)
                        dogInfoList.add(dogData!!)
                        activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                    } else {
                        val dogData = it.data?.getParcelableExtra<DogInfo>("InputDone")
                        dogInfoList.add(dogData!!)
                        activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    // 이벤트 설정
    fun setEvent(){
        activityMainBinding.apply {
            buttonInput.setOnClickListener {
                val intentAdd = Intent(this@MainActivity, AddActivity::class.java)
                addActivityLauncher.launch(intentAdd)
            }
        }
    }

    inner class RecyclerViewMainAdapter : RecyclerView.Adapter<RecyclerViewMainAdapter.ViewHolderMain>(){
        // ViewHodler
        inner class ViewHolderMain(rowMainBinding: RowMainBinding): RecyclerView.ViewHolder(rowMainBinding.root){
            val rowMainBinding: RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding

                // 항목 클릭시 전체가 선택되도록 가로, 세로 길이 설정
                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                // 항목 클릭시 ShowActivity로 이동
                this.rowMainBinding.root.setOnClickListener {
                    val intentShow = Intent(this@MainActivity, ShowActivity::class.java)
                    // 클릭한 정보 담기
                    intentShow.putExtra("DogData", dogInfoList[adapterPosition])
                    // Activity 시작
                    startActivity(intentShow)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val viewHolderMain = ViewHolderMain(rowMainBinding)
            return viewHolderMain
        }

        override fun getItemCount(): Int {
            return dogInfoList.size
        }

        override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
            holder.rowMainBinding.textViewName.text = "이름 : ${dogInfoList[position].name}"
            holder.rowMainBinding.textViewType.text = "견종 : ${dogInfoList[position].type}"
            holder.rowMainBinding.textViewAge.text = "나이 : ${dogInfoList[position].age}"
        }
    }
}