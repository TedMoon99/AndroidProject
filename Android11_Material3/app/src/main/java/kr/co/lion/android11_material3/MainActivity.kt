package kr.co.lion.android11_material3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// Material : 구글에서 만들어 배포하고 있는 UI 라이브러리
// android, flutter, figma 등등에서 사용할 수 있도록 배포하고 있다.
// 현재는 3 버전이다.
// https://m3.material.io

// Material 3 라이브러리를 이용하고자 한다면 build.gradle.kts(Module) 파일에
// 라이브러리를 셋팅해줘야 한다.
// => Andorid Studio 4.1 버전부터는 선언하지 않아줘도 된다. <= 프로젝트나 모듈 생성시에 자동으로 생성됨

// 최신 버전은 다음 페이지에서 확인한다.
// https://github.com/material-components/material-components-android/releases

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}