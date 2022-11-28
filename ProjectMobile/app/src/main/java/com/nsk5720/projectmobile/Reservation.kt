package com.nsk5720.projectmobile

import android.Manifest
import android.R
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.nsk5720.projectmobile.databinding.ActivityReservationBinding
import java.io.File

class Reservation : AppCompatActivity() {

    // 카메라 기능
    var photoUri: Uri? = null
    lateinit var cameraPermission:ActivityResultLauncher<String>
    lateinit var storagePermission:ActivityResultLauncher<String>
    lateinit var cameraLauncher:ActivityResultLauncher<Uri>
    lateinit var galleryLauncher:ActivityResultLauncher<String>


    val binding by lazy {ActivityReservationBinding.inflate(layoutInflater)}
    lateinit var activityResult: ActivityResultLauncher<String> // 카메라가 들어갈 수 도 있고 // 저장장소 //계약서 변수 설정
    //컨텐츠 내용 1번 스피너
    val listener by lazy {
        CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                when (buttonView.id) {
                    /* R.id.checkResin -> Log.d("CheckBox","레진이 선택되었습니다.")
                     R.id.checkInlay -> Log.d("CheckBox","바나나가 선택되었습니다.")
                     R.id.checkCrown -> Log.d("CheckBox","오렌지가 선택되었습니다.")
                     R.id.checkLaminate -> Log.d("CheckBox","사과가 선택 해제되었습니다.")
                     R.id.checkImplant -> Log.d("CheckBox","바나나가 선택 해제되었습니다.")
                     R.id.check1 -> Log.d("CheckBox","오렌지가 선택 해제되었습니다.")*/
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 카메라 권한 설정
        activityResult = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            if(it) {
                startProcess() // 승인이면 프로그램 진행
            } else{
                finish() // 미승인이면 앱 종료
            }
        }
        binding.buttonGallery.setOnClickListener{activityResult.launch(Manifest.permission.CAMERA)}
        binding.buttonCamera.setOnClickListener{activityResult.launch(Manifest.permission.CAMERA)}






        //컨텐츠 내용 1번 스피너
        var data = listOf("충치", "임플란트", "발치", "신경치료", "교정", "틀니", "심미",)
        var adapter = ArrayAdapter<String>(this, R.layout.simple_list_item_1, data)
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        // 컨텐츠 내용 2번 체크박스
        binding.checkResin.setOnCheckedChangeListener (listener)
        binding.checkInlay.setOnCheckedChangeListener (listener)
        binding.checkCrown.setOnCheckedChangeListener (listener)
        binding.checkLaminate.setOnCheckedChangeListener (listener)
        binding.checkImplant.setOnCheckedChangeListener (listener)
        binding.check1.setOnCheckedChangeListener (listener)

        //컨텐츠 내용 3번 시크바
        binding.seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.painResult.text = "$progress"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
        // intent 경로 설정
        val intent = Intent(this, CardReg::class.java)
        binding.payBtn.setOnClickListener {
            startActivity(intent)
        }

        val intent2 = Intent(this, Map::class.java)
        binding.resUntactRes.setOnClickListener {
            startActivity(intent2)
        }
        val intent3 = Intent(this, DiagnosisList::class.java)
        binding.resContactRes.setOnClickListener {
            startActivity(intent3)

        }
        val intent4 = Intent(this, ChatListActivity::class.java)
        binding.resDiagnosis.setOnClickListener {
            startActivity(intent4)
        }

        val intent5 = Intent(this, MyPage::class.java)
        binding.resMyPage.setOnClickListener {
            startActivity(intent5)
        }
        val intent6 = Intent(this, Map::class.java)
        binding.resBackBtn.setOnClickListener {
            startActivity(intent6)
        }

        // 카메라 기능
        storagePermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if(isGranted) {
                setViews()
            } else {
                Toast.makeText(baseContext, "외부저장소 권한을 승인해야 앱을 사용할 수 있습니다.", Toast.LENGTH_LONG).show()
                finish()
            }
        }

        cameraPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if(isGranted) {
                openCamera()
            } else {
                Toast.makeText(baseContext, "카메라 권한을 승인해야 카메라를 사용할 수 있습니다.", Toast.LENGTH_LONG).show()
            }
        }

        cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess  ->
            if(isSuccess) { binding.imagePreview.setImageURI(photoUri) }
        }

        galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            binding.imagePreview.setImageURI(uri)
        }

        storagePermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        // 카메라 기능 끝


    }


    //카메라 권한 설정 yes 시 toast실행
    fun startProcess(){
        Toast.makeText(this,"카메라를 실행합니다.", Toast.LENGTH_LONG).show()
    }   // 알림 팝업만 뜨도록 나중에는 카메라 실행 코드 넣을 부분이 되겠쬬쬬


    // 카메라 기능 시작
    fun setViews() {
        binding.buttonCamera.setOnClickListener {
            cameraPermission.launch(Manifest.permission.CAMERA)
        }
        binding.buttonGallery.setOnClickListener {
            openGallery()
        }
    }

    fun openCamera() {
        val photoFile = File.createTempFile(
            "IMG_",
            ".jpg",
            getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        )

        photoUri = FileProvider.getUriForFile(
            this,
            "${packageName}.provider",
            photoFile
        )

        cameraLauncher.launch(photoUri)
    }

    fun openGallery() {
        galleryLauncher.launch("image/*")
    }
    // 카메라 기능 끝

}