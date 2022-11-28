package com.nsk5720.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
// 메모기능
@Entity(tableName = "room_memo")
class RoomMemo {
    @PrimaryKey(autoGenerate = true)        //db속성 no에 primarykey줌
    @ColumnInfo
    var no: Long? = null

    @ColumnInfo
    var content: String = ""

    @ColumnInfo(name = "date")
    var datetime: Long = 0

    constructor(content: String, datetime: Long){
        this.content = content
        this.datetime = datetime
    }
}