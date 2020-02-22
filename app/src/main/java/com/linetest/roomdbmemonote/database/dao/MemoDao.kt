package com.linetest.roomdbmemonote.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.linetest.roomdbmemonote.database.Entity.MemoEntity
import io.reactivex.Maybe

@Dao
abstract class MemoDao : DefaultDao<MemoEntity>() {
    @Query("SELECT * FROM memo")
    abstract fun select(): Maybe<List<MemoEntity>>
}