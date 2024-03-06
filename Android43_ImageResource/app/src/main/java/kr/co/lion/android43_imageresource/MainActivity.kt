package kr.co.lion.android43_imageresource

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android43_imageresource.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }
}