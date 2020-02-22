package com.linetest.roomdbmemonote.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

abstract class DefaultDao<T> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(obj:T): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(obj: List<T>): List<Long>

    @Update(onConflict = OnConflictStrategy.ABORT)
    abstract fun update(obj: T)

    @Update(onConflict = OnConflictStrategy.ABORT)
    abstract fun update(obj: List<T>)
}