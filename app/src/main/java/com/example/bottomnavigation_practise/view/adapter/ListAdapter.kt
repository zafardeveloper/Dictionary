package com.example.bottomnavigation_practise.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation_practise.R
import com.example.bottomnavigation_practise.model.Dictionary.model.ListModel

class ListAdapter(private val listener: Listener) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var items = ArrayList<ListModel>()
    private var onFavoriteCheckedChangeListener: ((List<ListModel>) -> Unit)? = null


    fun updateItems(items: List<ListModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnFavoriteCheckedChangeListener(listener: (List<ListModel>) -> Unit) {
        onFavoriteCheckedChangeListener = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tajTextView = itemView.findViewById<TextView>(R.id.textTajWord)
        private val rusTextView = itemView.findViewById<TextView>(R.id.textRusWord)
        private val engTextView = itemView.findViewById<TextView>(R.id.textEngWord)
        private val transcription = itemView.findViewById<TextView>(R.id.transcription)
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


        fun bind(item: ListModel, listener: Listener) {
            tajTextView.text = item.tajWord
            rusTextView.text = item.rusWord
            engTextView.text = item.engWord
            transcription.text = item.transcription
            itemView.setOnClickListener {
                listener.onClick(item)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    interface Listener {
        fun onClick(item: ListModel)
    }

}