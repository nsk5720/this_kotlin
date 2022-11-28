package com.nsk5720.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RoomMemo::class), version = 1, exportSchema = false)
abstract class RoomHelper: RoomDatabase() {
    abstract fun roomMemoDao(): RoomMemoDao
}