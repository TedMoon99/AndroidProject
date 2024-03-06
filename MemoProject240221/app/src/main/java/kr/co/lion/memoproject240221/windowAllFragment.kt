package kr.co.lion.memoproject240221

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.memoproject240221.databinding.FragmentWindowAllBinding
import kr.co.lion.memoproject240221.databinding.RowWindowAllBinding

class windowAllFragment : Fragment() {

    lateinit var fragmentWindowAllBinding: FragmentWindowAllBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentWindowAllBinding = FragmentWindowAllBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        return fragmentWindowAllBinding.root
    }


    // RecyclerView 생성
    inner class RecyclerWindowAllAdapter() : RecyclerView.Adapter<RecyclerWindowAllAdapter.ViewHolderWindowAll>(){
        // ViewHolder 생성
        inner class ViewHolderWindowAll(rowWindowAllBinding: RowWindowAllBinding): RecyclerView.ViewHolder(rowWindowAllBinding.root){
            val rowWindowAllBinding : RowWindowAllBinding

            init {
                this.rowWindowAllBinding = rowWindowAllBinding
                // 항목 클릭 시 전체가 클릭이 될 수 있도록
                // 가로 세로 길이를 설정해준다.
                this.rowWindowAllBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                // 항목을 눌렀을 때의 리스너
            }


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderWindowAll {
            val rowWindowAllBinding = RowWindowAllBinding.inflate(layoutInflater)
            val viewHolderWindowAll = ViewHolderWindowAll(rowWindowAllBinding)
            return viewHolderWindowAll
        }

        override fun getItemCount(): Int {
            return 0
        }

        override fun onBindViewHolder(holder: ViewHolderWindowAll, position: Int) {
            holder.rowWindowAllBinding.textView.text
        }

    }
}