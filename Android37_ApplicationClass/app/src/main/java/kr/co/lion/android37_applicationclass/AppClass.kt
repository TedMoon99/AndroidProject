package kr.co.lion.android37_applicationclass

import android.app.Application

class AppClass : Application() {
    // Application을 상속받으며 android_manifest.xml 의 applicaiton 태그의 name 속성에 설정해준다.
    var value1 = 0
    var value2 = 0.0
    lateinit var value3:String
}