package com.nsk5720.room

import androidx.room.*      // 4개 다 써서 *로 바뀜
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface RoomMemoDao {
    @Query("select * from room_memo")
    fun getAll():List<RoomMemo>

    @Insert(onConflict = REPLACE)
    fun insert(memo:RoomMemo)

    @Delete
    fun delete(memo:RoomMemo)

    @Update
    fun update(memo:RoomMemo)

}