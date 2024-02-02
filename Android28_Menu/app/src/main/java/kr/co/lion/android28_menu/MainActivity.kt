package kr.co.lion.android28_menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.view.View.OnCreateContextMenuListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.PopupMenu
import androidx.core.view.forEachIndexed
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.android28_menu.databinding.ActivityMainBinding
import kr.co.lion.android28_menu.databinding.RowBinding

// Context Menu : View를 길게 누르면 타나타는 메뉴
// Popup Menu : 개발자가 코드를 통해 원하는 View에 띄우는 메뉴

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            recyclerView.apply {
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            // textView의 컨텍스트 메뉴
            // 첫 번째 : 메뉴를 구성하기 위해 사용할 메뉴 객체
            textView.setOnCreateContextMenuListener { menu, v, menuInfo ->
                // 메뉴의 헤더
                menu?.setHeaderTitle("텍스트 뷰의 메뉴입니다")
                // 메뉴를 구성한다.
                menuInflater.inflate(R.menu.textview_menu, menu)
                // 각 메뉴 아이템을 추출하여 리스너를 설정해준다.
                menu?.findItem(R.id.textview_menu_item1)?.setOnMenuItemClickListener { 
                    textView.text = "텍스트뷰의 메뉴 항목1 선택"
                    true
                }
                menu?.findItem(R.id.textview_menu_item2)?.setOnMenuItemClickListener { 
                    textView.text = "텍스트뷰의 메뉴 항목2 선택"
                     true
                }
            }

            // 팝업 메뉴
            button.setOnClickListener {
                // 팝업 메뉴를 생성한다.
                // 두 번째 매개 변수 : 메뉴를 띄울 View를 지정한다.
                val popupMenu = PopupMenu(this@MainActivity, textView)
                // 메뉴를 구성한다.
                menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
                // 메뉴의 항목을 눌렀을 때 동작할 리스너를 지정해준다.
                popupMenu.setOnMenuItemClickListener {
                    // 메뉴 항목의 id로 분기한다.
                    when(it.itemId){
                        R.id.popup_menu_item1 -> textView.text = "팝업 메뉴1을 선택했습니다"
                        R.id.popup_menu_item2 -> textView.text = "팝업 메뉴2를 선택했습니다"
                    }
                    true
                }
                // 메뉴를 보여준다.
                popupMenu.show()
            }
        }
    }

    // RecyclerView의 어뎁터
    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>(), OnCreateContextMenuListener{

        // ViewHolder
        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root){
            val rowBinding:RowBinding

            init {
                this.rowBinding = rowBinding

                rowBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                // RecyclerView 항목에 컨텍스트 메뉴를 설정해준다.
                rowBinding.root.setOnCreateContextMenuListener { menu, v, menuInfo ->
                    menu?.setHeaderTitle("${adapterPosition}번째 항목의 메뉴")
                    menuInflater.inflate(R.menu.recyclerview_menu, menu)

                    menu?.findItem(R.id.recyclerview_menu_item1)?.setOnMenuItemClickListener {
                        activityMainBinding.textView.text = "${adapterPosition}번째 항목의 메뉴1 선택"
                        true
                    }
                    menu?.findItem(R.id.recyclerview_menu_item2)?.setOnMenuItemClickListener {
                        activityMainBinding.textView.text = "${adapterPosition}번째 항목의 메뉴2 선택"
                        true
                    }
                }

            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowBinding = RowBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowBinding)
            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowBinding.textViewRow.text = "항목 $position"
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            val menuInfo2 = menuInfo as AdapterContextMenuInfo
            menu?.setHeaderTitle("${menuInfo2.position}번째 항목의 메뉴")
            menuInflater.inflate(R.menu.recyclerview_menu, menu)
        }
    }
}










