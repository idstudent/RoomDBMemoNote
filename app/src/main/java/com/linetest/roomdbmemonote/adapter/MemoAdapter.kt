package com.linetest.roomdbmemonote.adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linetest.roomdbmemonote.R
import com.linetest.roomdbmemonote.database.Entity.MemoEntity
import kotlinx.android.synthetic.main.item_memo.view.*

class MemoAdapter(
    private var context : Context,
    private var memoList : List<MemoEntity>
) : RecyclerView.Adapter<MemoAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_memo , parent, false)
        )

    override fun getItemCount(): Int = memoList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind()

    inner class ItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val item = memoList[adapterPosition]
            itemView.title.text = item.title
            itemView.contents.text = item.contents
        }
    }
}