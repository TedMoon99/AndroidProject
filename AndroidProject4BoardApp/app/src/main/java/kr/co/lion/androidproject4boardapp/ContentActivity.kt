
package kr.co.lion.androidproject4boardapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.androidproject4boardapp.databinding.ActivityContentBinding

class ContentActivity : AppCompatActivity() {
    lateinit var activityContentBinding: ActivityContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityContentBinding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(activityContentBinding.root)


        activityContentBinding.buttonTest.setOnClickListener{
            activityContentBinding.drawerLayoutContent.open()
        }
    }



}