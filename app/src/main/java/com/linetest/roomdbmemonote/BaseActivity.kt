package com.linetest.roomdbmemonote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.linetest.roomdbmemonote.database.DBManager
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity : AppCompatActivity() {
    protected  val compositeDisposable : CompositeDisposable = CompositeDisposable()
    protected lateinit var dbManager: DBManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        dbManager = DBManager.getInstance(this) ?: return
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
