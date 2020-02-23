package com.linetest.roomdbmemonote

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.linetest.roomdbmemonote.adapter.MemoAdapter
import com.linetest.roomdbmemonote.database.Entity.MemoEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity :  BaseActivity() {
    private var adapter: MemoAdapter? = null
    private var memoList: List<MemoEntity>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    override fun onResume() {
        super.onResume()
        drawList()

    }
    private fun init() {
        addBtn.setOnClickListener {
            startActivity(Intent(this, AddMemoActivity::class.java))
        }
    }
    private fun drawList() {
        val process = Observable.just(1)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .flatMap { getMemoList() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                adapter = MemoAdapter(this, memoList!!)
                recyclerMemoList.adapter = adapter
                recyclerMemoList.layoutManager =  LinearLayoutManager(this)
                adapter?.notifyDataSetChanged()
            }
        compositeDisposable.add(process)
    }
    private fun getMemoList() : Observable<List<MemoEntity>> {
        return dbManager.MemoDao().select().toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                memoList = it
                Log.e("tag", "list  "+memoList)
            }
    }
}