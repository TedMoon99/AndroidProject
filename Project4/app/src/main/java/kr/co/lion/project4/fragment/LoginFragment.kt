package kr.co.lion.project4.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.project4.MainActivity
import kr.co.lion.project4.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    lateinit var fragmentLoginBinding: FragmentLoginBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        setView()

        return fragmentLoginBinding.root
    }


    // View
    fun setView(){
        fragmentLoginBinding.apply {
            toolbarLogin.apply {
                // title
                title = "로그인"
                // menu

            }
        }
    }

    // Event
    fun setEvent(){
        fragmentLoginBinding.apply {

        }
    }
}