package com.linetest.roomdbmemonote.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.linetest.roomdbmemonote.database.Entity.MemoEntity
import com.linetest.roomdbmemonote.database.dao.MemoDao

/** entities는 테이블을 모아놓는 곳, 테이블마다 @Entity(tableName = 테이블명)으로 정의
 *  그다음 여기에 모아놓고 관리한다.
 *  exportSchema는 Room에 스키마를 폴더에 내보내도록 설정여부를 정한다는데 잘 모르겠음 구글보면 다
 *  false로 해둠
 */
@Database(entities = [
    MemoEntity::class
], version = 1, exportSchema = false)
abstract class DBManager : RoomDatabase() {
    abstract fun MemoDao() : MemoDao

    /* 싱글톤 사용, synchronized는 인스턴스 여러개 생성되는걸 방지
     * 싱글톤을 사용하면서 방지될 것같긴한데 혹여나 다른 경우로 생성되는걸 막기 위함인듯
     */
    companion object {
        private var INSTANCE : DBManager ?= null

        fun getInstance(context : Context) : DBManager? {
            if(INSTANCE == null) {
                synchronized(DBManager::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, DBManager::class.java, "memo.db").build()
                }
            }
            return INSTANCE
        }
    }
}