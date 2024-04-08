package com.example.bottomnavigation_practise.dictionary_list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation_practise.R
import com.example.bottomnavigation_practise.dictionary_list.model.ListModel

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private val dictionaryList = ArrayList<ListModel>()

    @SuppressLint("notifyDataSetChanged")
    fun updateItems(items: List<ListModel>) {
        this.dictionaryList.clear()
        this.dictionaryList.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tajTextView = itemView.findViewById<TextView>(R.id.textTajWord)
        private val rusTextView = itemView.findViewById<TextView>(R.id.textRusWord)

        fun bind(item: ListModel){
            tajTextView.text = item.tajWord
            rusTextView.text = item.rusWord
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dictionaryList[position])
    }

    override fun getItemCount(): Int = dictionaryList.size
}