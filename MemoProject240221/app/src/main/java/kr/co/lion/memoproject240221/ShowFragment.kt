package kr.co.lion.memoproject240221

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.memoproject240221.databinding.FragmentShowBinding

class ShowFragment : Fragment() {
    lateinit var fragmentShowBinding: FragmentShowBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentShowBinding = FragmentShowBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        setView()
        setEvent()

        return fragmentShowBinding.root
    }

    // View 설정
    fun setView(){
        fragmentShowBinding.apply {
            toolbarShow.apply {
                // title
                title = "메모 보기"
                // menu
                inflateMenu(R.menu.menu_show)

                // Navigation Icon
                setNavigationIcon(R.drawable.arrow_back_24px)
            }

            // AddFragment에서 받아온 데이터 보여주기

        }
    }
    // Event 설정
    fun setEvent(){
        fragmentShowBinding.apply {
            toolbarShow.apply {
                // menu 리스너
                setOnMenuItemClickListener {
                    when (it.itemId){
                        R.id.menuItemEdit -> { // 수정 메뉴
                            TODO("Edit Fragment 화면으로 이동")
                        }
                        R.id.menuItemDelete -> { // 삭제 메뉴
                            TODO("현재 메모 데이터 삭제")
                        }
                    }
                    true
                }

                // Navigation 리스너
                setNavigationOnClickListener {
                    // 아무런 일도 하지않고 AddFragment로 이동
                    mainActivity.removeFragment(FragmentName.SHOW_FRAGMENT)

                }
            }
        }
    }
}