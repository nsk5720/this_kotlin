package com.nsk5720.projectmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.nsk5720.projectmobile.databinding.ActivityMyPageBinding

class MyPage : AppCompatActivity() {
    val binding by lazy{ActivityMyPageBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent = Intent(this, DiagnosisList::class.java)
        binding.diagListBtn.setOnClickListener {
            startActivity(intent)
        }

        val intent2 = Intent(this, Map::class.java)
        binding.myPageUntactRes.setOnClickListener {
            startActivity(intent2)
        }

        val intent3 = Intent(this, DiagnosisList::class.java)
        binding.myPageContactRes.setOnClickListener {
            startActivity(intent3)
        }
        val intent4 = Intent(this, ChatListActivity::class.java)
        binding.myPageDiagnosis.setOnClickListener {
            startActivity(intent4)
        }

        val intent5 = Intent(this, SharedPreferences::class.java)
        binding.preferencesBtn.setOnClickListener {
            startActivity(intent5)
        }

        val intent6 = Intent(this, Memo::class.java)
        binding.memoBtn.setOnClickListener {
            startActivity(intent6)
        }


        //프래그먼트 목록을 생성
        val fragmentList = listOf(FragmentRecent(), FragmentEvent(),FragmentEvent2(),FragmentEvent3())
        //어댑터를 생성하고 앞에서 생성해둔 프래그먼트 목록을 저장
        val adapter = FragmentAdapter(this)
        adapter.fragmentList = fragmentList
        //레이아웃의 viewPager를 import하고 어댑터를 적용
        binding.viewPager.adapter = adapter

        //메뉴명으로 사용할 이름들을 배열에 저장
        val tabTitles = listOf("최근예약일", "S다인치과","사과나무\n치과","서울민플러스치과")

        //TabLayoutMediator를 사용해서 TabLayout과 뷰페이지를 연결
        TabLayoutMediator(binding.tabLayout, binding.viewPager) {tab, position ->
            //tab 파라미터의 text 속성에 앞에서 미리 정의해둔 메뉴명을 입력
            tab.text = tabTitles[position]
        }.attach()




    }
}