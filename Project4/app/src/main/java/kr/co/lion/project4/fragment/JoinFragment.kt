package kr.co.lion.project4.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.project4.MainActivity
import kr.co.lion.project4.databinding.FragmentJoinBinding


class JoinFragment : Fragment() {

    lateinit var fragmentJoinBinding: FragmentJoinBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentJoinBinding = FragmentJoinBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        return fragmentJoinBinding.root
    }

    // View
    fun setView(){
        fragmentJoinBinding.apply {
            toolbarJoin.apply {
                // title
                title = "Sign up"
                // menu

            }
        }
    }

    // Event
    fun setEvent(){
        fragmentJoinBinding.apply {

        }
    }

    // validation
    fun checkValidation(){

    }
}