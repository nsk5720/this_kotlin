package com.nsk5720.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nsk5720.fragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    lateinit var listFragment:ListFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        
        setFragment()

        binding.btnSend.setOnClickListener{
            listFragment.setValue("전달할 값")
        }
    }
    
    fun setFragment(){
        // ListFragment를 생성
        listFragment = ListFragment()
        // 프래그먼트 생성 시 값 전달하기 위한 변수 설정 부분
        var bundle = Bundle()
        bundle.putString("key1", "List Fragment")
        bundle.putInt("key2", 20220101)
        listFragment.arguments = bundle
        // 프래그먼트 메니저를 통해서 프랜잭션을 시작하고, 시작한 트랜잭션을 변수에 저장
        val transaction = supportFragmentManager.beginTransaction()
        // 트랜젝션의 add() 메서드로 frameLayout을 id로 가지고 있는 레이아웃 앞에서 생성한 listFragment 삽입
        transaction.add(R.id.frameLayout, listFragment)
        // setFragment() 메서드 안에 다음과 같이 ListFragment를 생성
        transaction.commit()
    }

    fun goDetail(){
        val detailFragment = DetailFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout, detailFragment)
        transaction.addToBackStack("detail")
        transaction.commit()
    }

    fun goBack(){
        onBackPressed()
    }
}