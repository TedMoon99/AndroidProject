package kr.co.lion.memoproject240221

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.memoproject240221.databinding.FragmentAddBinding
import java.text.SimpleDateFormat
import java.util.Date

class AddFragment : Fragment() {
    lateinit var fragmentAddBinding: FragmentAddBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentAddBinding = FragmentAddBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        setView()
        setEvent()

        return fragmentAddBinding.root
    }

    // View 설정
    fun setView(){
        fragmentAddBinding.apply {
            toolbarAdd.apply {
                // title
                title = "메모 추가"

                // menu
                inflateMenu(R.menu.menu_add)

                // NavigationIcon
                setNavigationIcon(R.drawable.arrow_back_24px)
            }
        }
    }

    // Event 설정
    fun setEvent(){
        fragmentAddBinding.apply {
            toolbarAdd.apply {
                // menu 리스너
                setOnMenuItemClickListener {
                    when (it.itemId){
                        R.id.menuItemAddDone -> {
                            // 데이터 가져오기기
                            val title = textFieldTitleAdd.text.toString()
                            val content = textFieldContentAdd.text.toString()
                            val temp = Date()
                            val formattedData = SimpleDateFormat("yyyy-MM-dd")
                            val date = formattedData.format(temp)

                            // MemoData 객체 생성
                            val memo = MemoData(title, content, date)

                            // 완료 메뉴를 누르면 메모를 저장하는 처리를 하고 ShowFragment 화면이 나타난다
                            val bundle = Bundle()
                            bundle.putParcelable("AddMemo", memo)
                            mainActivity.replaceFragment(FragmentName.SHOW_FRAGMENT, true, bundle) // bundle 객체 전달해서 데이터를 전달한다.
                        }
                    }
                    true
                }

                // Navigation 리스너
                setNavigationOnClickListener {
                    // 뒤로가기 버튼 클릭시 아무일 없이 MainFragment로 이동
                    mainActivity.removeFragment(FragmentName.ADD_FRAGMENT)
                }
            }
        }
    }
}