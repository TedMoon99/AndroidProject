package kr.co.lion.android31_br

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest


class MainActivity : AppCompatActivity() {

    val permissionList = arrayOf(
        Manifest.permission.RECEIVE_SMS,
        Manifest.permission.RECEIVE_BOOT_COMPLETED,
        Manifest.permission.READ_PHONE_STATE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermissions(permissionList, 0)
    }
}