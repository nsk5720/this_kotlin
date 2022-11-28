package com.nsk5720.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteHelper(context: Context, name: String, version: Int)
    : SQLiteOpenHelper(context,name,null, version) {
    // Context, 데이터베이스명, 팩토리, 버전정보 => SQLiteOpenHelper의 파라메터(4개) 필요
    override fun onCreate(db: SQLiteDatabase?) {
        // 처음 설치 후 DB가 없는 경우에만 실행
        val create = "create table memo (" +
                "no integer primary key, " +
                "content text, " +
                "datetime integer" +
                ")"
        db?.execSQL(create)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertMemo(memo:Memo){
        val values = ContentValues()
        values.put("content", memo.content)
        values.put("datetime", memo.datetime)

        val wd = writableDatabase
        wd.insert("memo",null,values)
        wd.close()
    }
    fun selectMemo(): MutableList<Memo> {       // 조회한 결과 값(return)을 담을 변수가 list
        val list = mutableListOf<Memo>()        // 전체 DB
        val select = "select * from memo"       // 전체 DB를 읽어줄 // 읽기전용 DB
        val rd =readableDatabase
        val cursor = rd.rawQuery(select, null)  // 전체 DB의 맨 위(초기화상태=null)
        while (cursor.moveToNext()){
            val noIdx = cursor.getColumnIndex("no")
            val contentIdx = cursor.getColumnIndex("content")   // contentIdx 에는 컨텐츠 내용이 아닌 getColumnIndex에 따라 no번째 숫자가 있음
            val dateIdx = cursor.getColumnIndex("datetime")

            val no = cursor.getLong(noIdx)   // 몇번째로 이동해
            val content = cursor.getString(contentIdx)  // 여기에는 실제 컨텐츠 내용이 들어감 숫자가 아닌
            val datetime = cursor.getLong(dateIdx)

            list.add(Memo(no,content,datetime))
        }
        cursor.close()
        rd.close()
        return list
    }

    fun updateMemo(memo: Memo) {
        val values = ContentValues()
        values.put("content",memo.content)
        values.put("datetime",memo.datetime)

        val wd = writableDatabase
        wd.update("memo", values, "no=${memo.no}", null)
        wd.close()
    }

    fun deleteMemo(memo:Memo) {
        val delete = "delete from memo where no=${memo.no}"
        val db = writableDatabase
        db.execSQL(delete)
        db.close()
    }






}


data class Memo(var no:Long?, var content:String, var datetime:Long)