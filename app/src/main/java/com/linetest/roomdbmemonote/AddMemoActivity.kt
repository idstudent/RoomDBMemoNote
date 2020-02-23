package com.linetest.roomdbmemonote

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.linetest.roomdbmemonote.database.Entity.MemoEntity
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_memo.*
import java.util.*

class AddMemoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_memo)

        init()
    }
    private fun init() {
        saveBtn.setOnClickListener {
            addDB()
            setResult(Activity.RESULT_OK)
        }
    }
    private fun addDB() {
        val process = Observable.just(
            MemoEntity(
                uid = 0,
                title = titleText.text.toString(),
                contents = contentText.text.toString()
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnNext{
                Log.e("tag", it.toString())
                dbManager.MemoDao().insert(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                Toast.makeText(applicationContext, "저장", Toast.LENGTH_SHORT).show()
            }
        compositeDisposable.add(process)
    }
}
