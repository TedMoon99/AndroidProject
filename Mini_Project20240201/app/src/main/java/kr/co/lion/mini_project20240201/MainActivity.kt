package kr.co.lion.mini_project20240201

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.mini_project20240201.databinding.ActivityMainBinding
import kr.co.lion.mini_project20240201.databinding.RowMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 화면 activity_report를 위한 런처
    lateinit var activityReportLauncher: ActivityResultLauncher<Intent>
    // 화면 activity_register를 위한 런처
    lateinit var activityRegisterLauncher: ActivityResultLauncher<Intent>

    // 동물정보 리스트 생성
    val animalList = mutableListOf<AnimalData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        setToolbar()
        setView()

    }
    // 이벤트 설정
    fun setEvent(){
        // "등록" or FloatinActionButton을 누르면 activity_register화면이 나타난다.

        // RecyclerView의 항목을 누르면 activity_report화면이 나타난다.

        // "필터" 메뉴를 누르면 다이얼로그를 띄운다.
        // 다이얼로그에는 동물의 종류를 선택할 수 있도록 한다.
        // "전체", "사자", "호랭이", "기린" 중 하나를 선택할 수 있도록 하며
        // "전체" 를 선택하면 전체 동물들 목록이 나오고
        // "사자", "호랑이", "기린" 중 하나를 선택하면 해당 동물들만 목록에 나오게 한다.
        // 처음 시작시 "전체"가 선택되어 있는 상태로 시작한다.

    }
    // 움하하 다시 한 번 Github 테스트용 주석

    // 기본 데이터 및 객체 셋팅
    fun initData(){
        // activity_register 런처
        val contract1 = ActivityResultContracts.StartActivityForResult()
        activityRegisterLauncher = registerForActivityResult(contract1){
            // 작업 결과가 OK라면
            if (it.resultCode == RESULT_OK){
                // 전달된 Intent가 있다면
                if(it.data != null){
                    // 동물 객체를 추출한다.
                    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU){
                        val animalData = it.data?.getParcelableExtra("animalData", AnimalData::class.java)
                        animalList.add(animalData!!)
                        activityMainBinding.mainRecyclerView.adapter?.notifyDataSetChanged()
                    }
                }
            }
        }
        // activity_report 런처
        val contract2 = ActivityResultContracts.StartActivityForResult()
        activityReportLauncher = registerForActivityResult(contract2){

        }

    }


    // 툴바 설정
    fun setToolbar(){
        activityMainBinding.apply {
            toolbarMain.apply {
                // 타이틀
                title = "동물원 관리"
                // 메뉴
                inflateMenu(R.menu.menu_main)

                // 메뉴의 리스너
                setOnMenuItemClickListener {
                    when (it.itemId){
                        R.id.menuItemRegisterFilter -> {

                        }
                    }
                    true
                }
            }
        }

    }

    // View 설정
    fun setView(){
        activityMainBinding.apply {

            // RecyclerView의 항목에는 동물의 종류와 동물의 이름을 표시한다.

            // RecyclerView
            mainRecyclerView.apply {
                // 어댑터
                adapter = RecyclerViewMainAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(this@MainActivity)
                // 데코레이션
                val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }


    // RecyclerView의 어뎁터
    inner class RecyclerViewMainAdapter : RecyclerView.Adapter<RecyclerViewMainAdapter.ViewHolderMain>(){
        // ViewHolder
        @SuppressLint("NotifyDataSetChanged")
        inner class ViewHolderMain (rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            val rowMainBinding:RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding

                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val viewHolderMain = ViewHolderMain(rowMainBinding)

            return viewHolderMain
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
            when(position % 3){
                0 -> {
                    holder.rowMainBinding.textViewRowMainName.text = "사자"
                    holder.rowMainBinding.imageViewRowMainType.setImageResource(R.drawable.lion)
                }
                1 -> {
                    holder.rowMainBinding.textViewRowMainName.text = "호랑이"
                    holder.rowMainBinding.imageViewRowMainType.setImageResource(R.drawable.tiger)
                }
                2 -> {
                    holder.rowMainBinding.textViewRowMainName.text = "기린"
                    holder.rowMainBinding.imageViewRowMainType.setImageResource(R.drawable.giraffe)
                }
            }
        }
    }
}
class AnimalData(var name: String?, var type: String?): Parcelable{
    constructor(parcel: Parcel): this(
        parcel.readString(),
        parcel.readString()
    ){
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(type)
    }
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AnimalData> {
        override fun createFromParcel(parcel: Parcel): AnimalData {
            return AnimalData(parcel)
        }
        override fun newArray(size: Int): Array<AnimalData?> {
            return arrayOfNulls(size)
        }
    }
}