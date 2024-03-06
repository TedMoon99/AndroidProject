package kr.co.lion.memoproject240221

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.memoproject240221.databinding.FragmentEditBinding
import kr.co.lion.memoproject240221.databinding.FragmentWindowAllBinding

class EditFragment : Fragment() {

    lateinit var fragmentEditBinding: FragmentEditBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentEditBinding = FragmentEditBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        setView()
        setEvent()

        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    // View 설정
    fun setView(){
        fragmentEditBinding.apply {
            toolbarEdit.apply {
                // title
                title = "메모 수정"
                // menu
                inflateMenu(R.menu.menu_edit)
                // NavigationIcon
                setNavigationIcon(R.drawable.arrow_back_24px)
            }

            TODO("ShowFragment에서 내용을 받아와서 화면에 출력한다!")
        }
    }
    // Event 설정
    fun setEvent(){
        fragmentEditBinding.apply {
            toolbarEdit.apply {
                // menu 리스너
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menuItemEditDone -> { // 수정처리를 하고 화면 ShowFragment로 돌아간다.
                            TODO("수정한 내용을 받아와서 ShowFragment에 전달을 해주어야 한다.")
                        }
                    }
                    true
                }

                // Navigation 리스너
                setNavigationOnClickListener {
                    // 아무런 일도 하지않고 ShowFragment로 돌아간다.

                }
            }
        }
    }
}