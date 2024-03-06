package kr.co.lion.question3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.question3.databinding.FragmentMainBinding
import kr.co.lion.question3.databinding.RowMainBinding

class MainFragment : Fragment() {
    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    val memoList = mutableListOf<MemoData>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity


        setView()

        return fragmentMainBinding.root
    }

    // View
    fun setView(){
        fragmentMainBinding.apply {
            recyclerViewMain.apply {
                // Adapter
                adapter = RecyclerMainAdapter()
                // LayoutManager
                layoutManager = LinearLayoutManager(requireContext())
                // Line
                val deco = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }


    inner class RecyclerMainAdapter : RecyclerView.Adapter<RecyclerMainAdapter.ViewHolderMain>(){
        // ViewHolder
        inner class ViewHolderMain(rowMainBinding: RowMainBinding): RecyclerView.ViewHolder(rowMainBinding.root){
            val rowMainBinding : RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding

                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                // 항목을 눌렀을 때의 리스너
                this.rowMainBinding.root.setOnClickListener {
                    // 첫 번째 항목의 리사이클러 뷰에서 할일의 항목을 누르면
                    // 할일의 정보를 자세히 볼수 있는 화면이 나타난다.
                    // 할일의 정보를 자세히 보는 화면의 툴바에는 수정, 삭제, 완료 메뉴가 있다.
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

        override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
            holder.rowMainBinding.textViewTitle.text = "제목 : ${memoList[position].title}"
            holder.rowMainBinding.textViewContent.text = "내용 : ${memoList[position].content}"
            holder.rowMainBinding.textViewDate.text = "날짜 : ${memoList[position].date}"
        }
    }
}