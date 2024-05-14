package com.example.bottomnavigation_practise.view.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation_practise.common.ClickAreaButton
import com.example.bottomnavigation_practise.R
import com.example.bottomnavigation_practise.model.Dictionary.model.dataSource.db.dictionary.DictionaryRepository
import com.example.bottomnavigation_practise.model.Dictionary.model.dataSource.db.dictionary.entity.DictionaryEntity
import com.example.bottomnavigation_practise.view.favorite.vm.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DictionaryAdapter(
    private var words: List<DictionaryEntity>,
    private val listener: Listener,
    private val repository: DictionaryRepository,
    private val sharedViewModel: SharedViewModel
) : RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder>() {



    fun updateItem(newWords: List<DictionaryEntity>) {
        this.words = newWords
        notifyDataSetChanged()
    }


    class DictionaryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tajTextView = itemView.findViewById<TextView>(R.id.textTajWord)
        private val rusTextView = itemView.findViewById<TextView>(R.id.textRusWord)
        private val engTextView = itemView.findViewById<TextView>(R.id.textEngWord)
        private val transcription = itemView.findViewById<TextView>(R.id.transcription)
        internal val btnFavourite = itemView.findViewById<ImageView>(R.id.btnFavorite)

        fun bind(item: DictionaryEntity, listener: Listener, repository: DictionaryRepository) {
            tajTextView.text = item.wordTj
            rusTextView.text = item.wordRu
            engTextView.text = item.wordEng
            transcription.text = item.transcription

            ClickAreaButton.clickAreaButton(btnFavourite)

            btnFavourite.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    if (item.isFavorite == 1) {
                        item.isFavorite = 0
                    } else {
                        item.isFavorite = 1
                    }
                    repository.asyncUpdateFavoriteStatus(item)
                    withContext(Dispatchers.Main) {
                        if (item.isFavorite == 1) {
                            btnFavourite.setImageResource(R.drawable.favourite_icon)
                        } else {
                            btnFavourite.setImageResource(R.drawable.favourite_not_icon)
                        }
                    }
                }
            }


            itemView.setOnClickListener {
                listener.onClick(item)
                true
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryViewHolder {
        return DictionaryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DictionaryViewHolder, position: Int) {
        val word = words[position]
        holder.bind(word, listener, repository)

        if (word.isFavorite == 1) {
            holder.btnFavourite.setImageResource(R.drawable.favourite_icon)
        } else {
            holder.btnFavourite.setImageResource(R.drawable.favourite_not_icon)
        }

    }

    override fun getItemCount(): Int = words.size

    interface Listener {
        fun onClick(item: DictionaryEntity)

    }

}