package kr.co.lion.mynotebook1

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.mynotebook1.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    lateinit var activityEditBinding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityEditBinding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(activityEditBinding.root)
    }
}