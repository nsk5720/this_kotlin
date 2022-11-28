package com.nsk5720.permisson

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.nsk5720.permisson.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}

    lateinit var activityResult: ActivityResultLauncher<String> // 카메라가 들어갈 수 도 있고 // 저장장소 //계약서 변수 설정


    override fun onCreate(savedInstanceState: Bundle?) {    //무조건 실행하는 onCreate
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        activityResult = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            if(it) {
                startProcess() // 승인이면 프로그램 진행
            } else{
                finish() // 미승인이면 앱 종료
            }
        }
        binding.btnCamera.setOnClickListener{activityResult.launch(Manifest.permission.CAMERA)}
    }

    fun startProcess(){
        Toast.makeText(this,"카메라를 실행합니다.",Toast.LENGTH_LONG).show()
    }   // 알림 팝업만 뜨도록 나중에는 카메라 실행 코드 넣을 부분이 되겠쬬쬬
}