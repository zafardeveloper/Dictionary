package com.example.bottomnavigation_practise.model.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation_practise.R
import com.example.bottomnavigation_practise.model.model.ListModel
import com.example.bottomnavigation_practise.model.model.Word

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var items = ArrayList<Word>()
    private var onFavoriteCheckedChangeListener: ((List<Word>) -> Unit)? = null


    fun updateItems(items: List<Word>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
    fun setOnFavoriteCheckedChangeListener(listener: (List<Word>) -> Unit) {
        onFavoriteCheckedChangeListener = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tajTextView = itemView.findViewById<TextView>(R.id.textTajWord)
        private val rusTextView = itemView.findViewById<TextView>(R.id.textRusWord)
        private val favoriteRadioButton = itemView.findViewById<RadioButton>(R.id.radioBtnFavorite)



        fun bind(word: Word){
            tajTextView.text = word.tajWord
            rusTextView.text = word.rusWord
            favoriteRadioButton.isChecked = word.isFavorite

            favoriteRadioButton.setOnCheckedChangeListener { _, isChecked ->
                // Установим состояние избранности для выбранного элемента
                word.isFavorite = isChecked
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}