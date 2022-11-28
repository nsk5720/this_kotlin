package com.nsk5720.firebasechat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.nsk5720.firebasechat.databinding.ActivityChatRoomBinding
import com.nsk5720.firebasechat.databinding.ItemMsgListBinding
import com.nsk5720.firebasechat.model.Message
import com.nsk5720.firebasechat.model.Room

class ChatRoomActivity : AppCompatActivity() {
    val binding by lazy { ActivityChatRoomBinding.inflate(layoutInflater)}
    val database = Firebase.database("https://this-is-android-with-kot-aee02-default-rtdb.asia-southeast1.firebasedatabase.app/")
    lateinit var msgRef: DatabaseReference

    var roomId: String = ""     //방 아이디디
   var roomTitle: String =""   // 방 이름

    //<Message> model 패키지를 import 할 것!
    val msgList = mutableListOf<Message>()  //파이어베이스에서 데이터를 불러온 후 저장할 변수
    lateinit var adapter: MsgListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*roomId, roomTitle 그리고 메시지 목록과 어뎁터를 저장할 프로퍼티를 아래에서 미리 선언함.
        메시지 목록을 컨트롤하는 어뎁터의 이름은 MsgListAdapter(맨 아래쪽 하단 클래스)를 사용한다*/
        // 인텐트로 전달된 방 정보와 사용자 정보 꺼내기
        roomId = intent.getStringExtra("roomId")?: "none"
        roomTitle = intent.getStringExtra("roomTitle")?: "없음"

        // 메시지 노드 레퍼런스 연결
        msgRef = database.getReference("rooms").child(roomId).child("messages")

        // 아답터를 생성하고 뷰와 연결
        adapter = MsgListAdapter(msgList)
        with(binding){
            // TextView에 방 제목을 입력하고 뒤로가기 버튼과 전송 버튼에 클릭리스너를 달아서 각각 호출
            recyclerMsgs.adapter = adapter
            recyclerMsgs.layoutManager = LinearLayoutManager(baseContext)

            textTitle.setText(roomTitle)
            btnBack.setOnClickListener{finish()}
            btnSend.setOnClickListener{sendMsg()}
        }
        loadMsgs()
    }

    fun loadMsgs() {
        msgRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // 메시지 목록 삭제
                msgList.clear()
                for (item in snapshot.children) {
                    item.getValue(Message::class.java)?.let { msg ->
                        msgList.add(msg) // 메시지 목록에 추가
                    }
                }
                // 아답터 갱신
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        })
    }

    fun sendMsg() {
        with(binding) {
            if(editMsg.text.isNotEmpty()) { // 입력 값이 있을 때만 처리
                val message = Message(editMsg.text.toString(), ChatListActivity.userName)
                val msgId = msgRef.push().key!!
                message.id = msgId
                msgRef.child(msgId).setValue(message)
                // 메시지 전송 후 입력필드 삭제
                editMsg.setText("")
            }
        }
    }
}


class MsgListAdapter(val msgList:MutableList<Message>)
    : RecyclerView.Adapter<MsgListAdapter.Holder>(){
    // 뷰생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MsgListAdapter.Holder {
        val binding = ItemMsgListBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return Holder(binding)
    }
    // 바인딩 처리
    override fun onBindViewHolder(holder: MsgListAdapter.Holder, position: Int) {
        val msg = msgList.get(position)
        holder.setMsg(msg)
    }
    // 목록의 개수
    override fun getItemCount(): Int {
        return msgList.size
    }

    class Holder(val binding: ItemMsgListBinding):
            RecyclerView.ViewHolder(binding.root){
                fun setMsg(msg:Message){
                    binding.textName.setText(msg.userName)
                    binding.textMsg.setText(msg.msg)
                    binding.textDate.setText("${msg.timestamp}")
                }
            }
}