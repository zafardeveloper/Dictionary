package com.example.bottomnavigation_practise.view.adapter

import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation_practise.R
import com.example.bottomnavigation_practise.model.Dictionary.model.ListModel
import com.example.bottomnavigation_practise.model.Dictionary.model.dataSource.db.dictionary.entity.DictionaryEntity
import java.util.Locale

class DictionaryAdapter(
    private var words: List<DictionaryEntity>, private val listener: Listener
) : RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder>() {

//    private var items = ArrayList<ListModel>()
    private var onFavoriteCheckedChangeListener: ((List<ListModel>) -> Unit)? = null


    fun updateItems(words: List<DictionaryEntity>) {
        this.words = words.toList()
        notifyDataSetChanged()
    }

    fun setOnFavoriteCheckedChangeListener(listener: (List<ListModel>) -> Unit) {
        onFavoriteCheckedChangeListener = listener
    }

    class DictionaryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tajTextView = itemView.findViewById<TextView>(R.id.textTajWord)
        private val rusTextView = itemView.findViewById<TextView>(R.id.textRusWord)
        private val engTextView = itemView.findViewById<TextView>(R.id.textEngWord)
//        private val transcription = itemView.findViewById<TextView>(R.id.transcription)
        private val btnFavourite = itemView.findViewById<ImageView>(R.id.btnFavorite)
        private var isImagePressed = false
        private val imageViewPlay = view.findViewById<ImageView>(R.id.imageViewPlay)
        private var textToSpeech: TextToSpeech? = null

        init {
            imageViewPlay.setOnClickListener {
                val context = itemView.context
                val text = itemView.findViewById<TextView>(R.id.textTajWord).text.toString() // Здесь вы можете выбрать любой текст для воспроизведения
                textToSpeech = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
                    if (status != TextToSpeech.ERROR) {
                        textToSpeech?.language = Locale.getDefault()
                        textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
                    }
                })
            }
            btnFavourite.setOnClickListener {
                isImagePressed = !isImagePressed
                if (isImagePressed) {
                    btnFavourite.setImageResource(R.drawable.favourite_icon)
                } else {
                    btnFavourite.setImageResource(R.drawable.favourite_not_icon)
                }
            }
        }


        fun bind(item: DictionaryEntity, listener: Listener) {
            tajTextView.text = item.wordTj
            rusTextView.text = item.wordRu
            engTextView.text = item.wordEng
//            transcription.text = item.transcription
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
        holder.bind(word, listener)
    }

    override fun getItemCount(): Int = words.size

    interface Listener {
        fun onClick(item: DictionaryEntity)
    }

}