package com.nsk5720.projectmobile

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.LinearLayoutManager
import com.nsk5720.projectmobile.databinding.ActivityMainBinding
import androidx.activity.result.contract.ActivityResultContracts
import com.nsk5720.projectmobile.databinding.ActivityMusicPlayBinding

class MusicPlay : BaseActivity() {
    lateinit var storagePermission: ActivityResultLauncher<String>
    val binding by lazy { ActivityMusicPlayBinding.inflate(layoutInflater) }

    override fun permissionGranted(requestCode: Int) {
        startProcess()
    }

    override fun permissionDenied(requestCode: Int) {
        Toast.makeText(this
            , "외부저장소 권한 승인이 필요합니다. 앱을 종료합니다."
            , Toast.LENGTH_LONG)
            .show()
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requirePermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 999)
    }

    fun startProcess() {
        setContentView(binding.root)

        val adapter = MusicRecyclerAdapter()
        adapter.musicList.addAll(getMusicList())

        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
    }

    fun getMusicList() : List<Music> {
        // 1. 음원 정보 주소
        val listUrl = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        // 2. 음원 정보 컬럼들
        val proj = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.DURATION
        )
        // 3. 컨텐트리졸버의 쿼리에 주소와 컬럼을 입력하면 커서형태로 반환받는다
        val cursor = contentResolver.query(listUrl, proj, null, null, null)
        val musicList = mutableListOf<Music>()
        while (cursor?.moveToNext() == true) {
            val id = cursor.getString(0)
            val title = cursor.getString(1)
            val artist = cursor.getString(2)
            val albumId = cursor.getString(3)
            val duration = cursor.getLong(4)

            val music = Music(id, title, artist, albumId, duration)
            musicList.add(music)
        }
        return musicList
    }
}






