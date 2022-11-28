package com.nsk5720.projectmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.nsk5720.projectmobile.ChatListActivity
import com.nsk5720.firebasechat.model.User
import com.nsk5720.projectmobile.databinding.ActivityLoginBinding




class Login : AppCompatActivity() {
    val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    val database =
        Firebase.database("https://dencarefirebase-default-rtdb.asia-southeast1.firebasedatabase.app")
    val usersRef = database.getReference("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val intent2 = Intent(this, Join1::class.java)
        binding.joinBtn.setOnClickListener {
            startActivity(intent2)
        }

        //val intent2 = Intent(this, Map::class.java)
        //binding.btnSignin.setOnClickListener {
        //    startActivity(intent2)
        //}

        with(binding) {
            binding.btnSignin.setOnClickListener { signin() }
            //binding.btnSignup.setOnClickListener { signup() }
        }
    }

    /* 회원가입과 마찬가지로 userRef.child(아이디).get()를 사용해서 해당 아이디와 노드 존재 유무
     를 검사해야 한다. */
    fun signin() {
        with(binding) {
            // 1. 입력 된 값을 가져오고
            val id = editId.text.toString()
            val password = editPassword.text.toString()
            if (id.isNotEmpty() && password.isNotEmpty()) {
                // 2. 아이디로 User 데이터 가져오기
                usersRef.child(id).get().addOnSuccessListener {
                    // 2.1 id 존재 확인
                    if (it.exists()) {
                        it.getValue(User::class.java)?.let { user ->
                            // 2.1.1 비밀번호 비교 후 같으면 채팅방 목록이동
                            if (user.password == password) {
                                goChatroomList(user.id, user.name)
                            } else {
                                Toast.makeText(baseContext, "비밀번호가 다릅니다.", Toast.LENGTH_LONG).show()
                            }
                        }
                    } else {
                        Toast.makeText(baseContext, "아이디가 없습니다.", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(
                    baseContext,
                    "아이디, 비밀번호를  입력해야 합니다.",
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }
    }

    /* 로그인 성공 시 채팅방 목록으로 넘어가는 메서드. signin()에서 호출한 메서드를 사용하면 된다.
    채팅방 목록에서 현재 사용자 정보가 필요하기 때문에 putExtra로 아이디와 이름을 전달한다. */
    fun goChatroomList(userId: String, userName: String) {
        val intent = Intent(this, ChatListActivity::class.java)
        intent.putExtra("userId", userId)
        intent.putExtra("userName", userName)
        startActivity(intent)
    }
}

