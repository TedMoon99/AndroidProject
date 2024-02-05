package kr.co.lion.android04_linearlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// LinearLayout
// 방향성을 가지고 View 들을 배치하는 레이아웃
// 주요 속성
// orientation : 뷰가 배치되는 방향을 결정한다.
//      vertical : 세로 방향
//      horizontal(생략) : 가로 방향
// weight : 배치되는 뷰의 크기 비율
// width나 height를 0dp로 설정하고 weight을 1로 설정하여 가중치를 설정해줄 수도 있다.
// Ex) 3개의 View가 수직(vertical)이나 수평(horizontal)으로 존재할 경우를 생각해보자.
// i) 수직(vertical)로 배치한 경우
//  ### 
//  3개의 View들의 height를 모두 0dp로 설정하고 weight을 1로 지정하면 부모 레이아웃의 높이를 정확히 3등분하여 나눠가질 것이다.
//  3개의 View들의 height을 모두 0dp로 설정하고 weight을 각각 1, 2, 1로 지정하면 어떻게 될까?
//  이 경우에는 부모 레이아웃의 높이를 정확히 4등분하여 첫번째와 마지막 View는 하나씩 가질 것이고 2번째 View만 2개의 높이를 가지게 될 것이다.
//  ###

ii) 수평(horizontal)로 배치한 경우
// ###
// 3개의 View들의 width를 모두 0dp로 설정하고 weight 1로 지정하면 부모 레이아웃의 너비를 정확히 3등분하여 나눠가질 것이고,
// 3개의 View들의 width를 모두 0dp로 설정하고 weight을 각각 1, 2, 1로 지정해주면 부모 레이아웃의 너비를 정확히 4등분하여
// 첫번째와 마지막 View들은 하나씩의 너비를 가질 것이고 두번째 View는 2개의 너비를 가질 수 있다.
// ###

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
