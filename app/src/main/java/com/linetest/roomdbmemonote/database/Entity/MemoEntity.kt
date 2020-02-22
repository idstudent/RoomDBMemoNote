package com.linetest.roomdbmemonote.database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo")
data class MemoEntity(
    @PrimaryKey(autoGenerate = true)
    val uid : Int,
    val title : String,
    val contents : String
)