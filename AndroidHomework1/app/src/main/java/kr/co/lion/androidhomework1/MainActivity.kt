package kr.co.lion.androidhomework1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.androidhomework1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var  activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val a1 = 10
        val a2 = 20


    }


}