package com.example.bottomnavigation_practise.view.adapter

import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation_practise.R
import com.example.bottomnavigation_practise.model.Dictionary.model.DictionaryRepository
import com.example.bottomnavigation_practise.model.Dictionary.model.dataSource.db.dictionary.entity.DictionaryEntity
import com.example.bottomnavigation_practise.view.favorite.vm.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale

class FavoriteWordsAdapter(
    private var favoriteWords: List<DictionaryEntity>,
    private val listener: DictionaryAdapter.Listener,
    private val repository: DictionaryRepository,
    private val sharedViewModel: SharedViewModel
) : RecyclerView.Adapter<FavoriteWordsAdapter.FavoriteWordsViewHolder>() {

    class FavoriteWordsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tajTextView = itemView.findViewById<TextView>(R.id.textTajWord)
        private val rusTextView = itemView.findViewById<TextView>(R.id.textRusWord)
        private val engTextView = itemView.findViewById<TextView>(R.id.textEngWord)
        private val transcription = itemView.findViewById<TextView>(R.id.transcription)
        internal val btnFavourite = itemView.findViewById<ImageView>(R.id.btnFavorite)
        private val imageViewPlay = view.findViewById<ImageView>(R.id.imageViewPlay)
        private var textToSpeech: TextToSpeech? = null

        init {
            imageViewPlay.setOnClickListener {
                val context = itemView.context
                val text = itemView.findViewById<TextView>(R.id.textTajWord).text.toString()
                textToSpeech = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
                    if (status != TextToSpeech.ERROR) {
                        textToSpeech?.language = Locale.getDefault()
                        textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
                    }
                })
            }
        }


        fun bind(item: DictionaryEntity, listener: DictionaryAdapter.Listener, sharedViewModel: SharedViewModel, repository: DictionaryRepository) {
            tajTextView.text = item.wordTj
            rusTextView.text = item.wordRu
            engTextView.text = item.wordEng
            transcription.text = item.transcription

            btnFavourite.setOnClickListener {
                if (item.isFavorite == 1) {
                    btnFavourite.setImageResource(R.drawable.favourite_not_icon)
                    item.isFavorite = 0
                } else {
                    btnFavourite.setImageResource(R.drawable.favourite_icon)
                    item.isFavorite = 1
                }
                sharedViewModel.selectedWord.value = item
                CoroutineScope(Dispatchers.IO).launch {
                    repository.asyncUpdateFavoriteStatus(item)
                }
            }
            itemView.setOnClickListener {
                listener.onClick(item)
                true
            }
        }
    }

    fun updateItem(newWords: List<DictionaryEntity>) {
        this.favoriteWords = newWords.toList()
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteWordsViewHolder {
        return FavoriteWordsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int = favoriteWords.size

    override fun onBindViewHolder(holder: FavoriteWordsViewHolder, position: Int) {
        val word = favoriteWords[position]
        holder.bind(word, listener, sharedViewModel, repository)

        if (word.isFavorite == 1) {
            holder.btnFavourite.setImageResource(R.drawable.favourite_icon)
        } else {
            holder.btnFavourite.setImageResource(R.drawable.favourite_not_icon)
        }
    }

}