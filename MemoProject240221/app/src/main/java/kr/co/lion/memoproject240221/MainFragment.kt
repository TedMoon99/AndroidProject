package kr.co.lion.memoproject240221

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import kr.co.lion.memoproject240221.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        mainActivity = activity as MainActivity // Activity의 주소값을 담아준다

        setEvent()
        setView()

        return fragmentMainBinding.root
    }

    // View 설정
    fun setView(){
        fragmentMainBinding.apply {
            toolbarMainFragment.apply {
                // title
                title = "메모"
                // 메뉴
                inflateMenu(R.menu.menu_main)
            }
        }
    }

    // Event 설정
    fun setEvent(){
        fragmentMainBinding.apply {
            toolbarMainFragment.apply {
                // menu 리스너
                setOnMenuItemClickListener {
                    when (it.itemId){
                        R.id.menuItemCalendar -> { // 달력 클릭
                            // menuItem 달력 없앰
                            visibility = View.GONE
                            // menuItem 전체 보여줌

                            // window -> Calender & RecyclerView 화면으로 이동
                            window.apply {
                                mainActivity.replaceFragment(FragmentName.WINDOW_CALENDAR_FRAGMENT, false, null)
                            }
                        }
                        R.id.menuItemAll -> { // 전체 클릭
                            // menuItem 전체 없앰
                            visibility = View.GONE
                            // menuItem 달력 없앰

                            // window -> ShowMemoList(모든 메모의 항목들을 보여줌) 화면으로 이동
                            window.apply {
                                mainActivity.replaceFragment(FragmentName.WINDOW_ALL_FRAGMENT,false, null)
                            }

                        }
                        R.id.menuItemAdd -> { // 추가 클릭
                            // AddFragment 화면으로 이동
                            mainActivity.replaceFragment(FragmentName.ADD_FRAGMENT,true, null)
                        }
                    }
                    true
                }
            }
        }
    }
}