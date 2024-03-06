package kr.co.lion.memoproject240221

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.memoproject240221.databinding.FragmentWindowCalendarBinding

class windowCalendarFragment : Fragment() {
    lateinit var fragmentWindowCalendarBinding: FragmentWindowCalendarBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentWindowCalendarBinding = FragmentWindowCalendarBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        return fragmentWindowCalendarBinding.root
    }

    //
}