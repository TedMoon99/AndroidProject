package kr.co.lion.android31_br

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class BootReveuver : BroadcastReceiver() {

    // 사건이 발생했을 때 안드로이드 OS가 호출하는 메서드
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val t1 = Toast.makeText(context, "부팅이 완료되었습니다.", Toast.LENGTH_SHORT)
        t1.show()

        Log.d("test1234", "부팅이 완료되었습니다")
    }
}