package kr.co.lion.memoproject240221

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kr.co.lion.memoproject240221.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 이전 Fragment
    var oldFragment: Fragment? = null
    // 새로 나타날 Fragment
    var newFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // MainFragment가 보여지도록 한다.
        replaceFragment(FragmentName.MAIN_FRAGMENT, false, null)
    }
    fun replaceFragment(name:FragmentName, addToBackStack:Boolean, data:Bundle?){

        SystemClock.sleep(200)

        // Fragment를 교체할 수 있는 객체를 추출한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // oldFragment에 newFragment가 가지고 있는 Fragment 객체를 담아준다.
        if(newFragment != null){
            oldFragment = newFragment
        }

        // 이름으로 분기한다.
        // Fragment의 객체를 생성하여 변수에 담아준다.
        when(name){
            FragmentName.MAIN_FRAGMENT -> {
                newFragment = MainFragment()
            }
            FragmentName.ADD_FRAGMENT -> {
                newFragment = AddFragment()
            }
            FragmentName.SHOW_FRAGMENT -> {
                newFragment = ShowFragment()
            }
            FragmentName.EDIT_FRAGMENT -> {
                newFragment = EditFragment()
            }
            FragmentName.WINDOW_CALENDAR_FRAGMENT -> {
                newFragment = windowCalendarFragment()
            }
            FragmentName.WINDOW_ALL_FRAGMENT -> {
                newFragment = windowAllFragment()
            }
        }

        // 새로운 Fragment에 전달할 객체가 있다면 arguments 프로퍼티에 넣어준다.
        if(data != null){
            newFragment?.arguments = data
        }

        if(newFragment != null){
            // Fragment를 교체한다.(이전 Fragment가 없으면 새롭게 추가하는 역할을 수행한다)
            // 첫 번째 매개 변수 : Fragment를 배치할 FragmentContainerView의 ID
            // 두 번째 매개 변수 : 보여주고하는 Fragment 객체를
            fragmentTransaction.replace(R.id.mainContainer, newFragment!!)

            // addToBackStack 변수의 값이 true면 새롭게 보여질 Fragment를 BackStack에 포함시켜 준다.
            if(addToBackStack == true){
                // BackStack 포함 시킬때 이름을 지정해주면 원하는 Fragment를 BackStack에서 제거할 수 있다.
                fragmentTransaction.addToBackStack(name.str)
            }
            // Fragment 교체를 확정한다.
            fragmentTransaction.commit()
        }
    }
    fun removeFragment(name:FragmentName){
        SystemClock.sleep(200)

        // 지정한 이름으로 있는 Fragment를 BackStack에서 제거한다.
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}
enum class FragmentName(var str: String){
    MAIN_FRAGMENT("mainFragment"),
    ADD_FRAGMENT("addFragment"),
    SHOW_FRAGMENT("showFragment"),
    EDIT_FRAGMENT("editFragment"),
    WINDOW_CALENDAR_FRAGMENT("windowCalendarFragment"),
    WINDOW_ALL_FRAGMENT("windowAllFragment")
}