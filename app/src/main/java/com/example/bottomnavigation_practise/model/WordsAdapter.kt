package com.alif.practicefragmentandrecyclerview.chats.adapter


import android.app.AlertDialog
import android.app.PendingIntent.getActivity
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alif.practicefragmentandrecyclerview.chats.model.WordModel
import com.example.bottomnavigation_practise.R


class WordsAdapter : RecyclerView.Adapter<WordsAdapter.ViewHolder>() {

    private val items = ArrayList<WordModel>()

    fun updateItems(items: List<WordModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private val tajTextView = itemView.findViewById<TextView>(com.example.bottomnavigation_practise.R.id.tajTextView)
        private val rusTextView = itemView.findViewById<TextView>(com.example.bottomnavigation_practise.R.id.rusTextView)
        private val engTextView = itemView.findViewById<TextView>(com.example.bottomnavigation_practise.R.id.engTextView)

        fun bind(item: WordModel) {
            tajTextView.text = item.taj
            rusTextView.text = item.rus
            engTextView.text = item.eng

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_word_item, parent, false)

        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }


    }



