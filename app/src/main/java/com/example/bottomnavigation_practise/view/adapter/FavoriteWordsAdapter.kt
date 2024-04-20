package com.example.bottomnavigation_practise.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation_practise.R
import com.example.bottomnavigation_practise.model.Dictionary.model.ListModel

class FavoriteWordsAdapter(private val favoriteWords: List<ListModel>) : RecyclerView.Adapter<FavoriteWordsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewWord: TextView = itemView.findViewById(R.id.textTajWord)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val word = favoriteWords[position]
        holder.textViewWord.text = word.tajWord // Предполагается, что тут будет отображаться текст избранного слова
    }

    override fun getItemCount(): Int {
        return favoriteWords.size
    }
}