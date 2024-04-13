package com.example.bottomnavigation_practise.model.Dictionary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation_practise.R
import com.example.bottomnavigation_practise.model.Dictionary.model.Word

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
        private val btnFavourite = itemView.findViewById<ImageView>(R.id.btnFavorite)
        private var isImagePressed = false

        init {
            btnFavourite.setOnClickListener {
                isImagePressed = !isImagePressed
                if (isImagePressed) {
                    btnFavourite.setImageResource(R.drawable.favourite_icon)
                } else {
                    btnFavourite.setImageResource(R.drawable.favourite_not_icon)
                }
            }
        }


        fun bind(word: Word) {
            tajTextView.text = word.tajWord
            rusTextView.text = word.rusWord
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