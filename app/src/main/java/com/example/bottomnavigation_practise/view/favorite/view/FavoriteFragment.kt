package com.example.bottomnavigation_practise.view.favorite.view

import android.app.AlertDialog
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation_practise.common.ClickAreaButton
import com.example.bottomnavigation_practise.R
import com.example.bottomnavigation_practise.model.Dictionary.model.dataSource.db.dictionary.DictionaryRepository
import com.example.bottomnavigation_practise.model.Dictionary.model.dataSource.db.dictionary.entity.DictionaryEntity
import com.example.bottomnavigation_practise.view.favorite.adapter.FavoriteWordsAdapter
import com.example.bottomnavigation_practise.view.home.adapter.DictionaryAdapter
import com.example.bottomnavigation_practise.view.favorite.vm.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class FavoriteFragment :
    Fragment(R.layout.fragment_second),
    DictionaryAdapter.Listener, TextToSpeech.OnInitListener {
    private var textToSpeechRu: TextToSpeech? = null
    private var textToSpeechEng: TextToSpeech? = null

    private val recyclerView by lazy {
        requireView().findViewById<RecyclerView>(R.id.recyclerViewFavorite)
    }
    private val sharedViewModel: SharedViewModel by viewModels()

    private lateinit var favoriteWordsAdapter: FavoriteWordsAdapter
    private val favoriteWordsRepository: DictionaryRepository = DictionaryRepository.Base()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textToSpeechRu = TextToSpeech(requireContext(), this)
        textToSpeechEng = TextToSpeech(requireContext(), this)


        favoriteWordsAdapter =
            FavoriteWordsAdapter(emptyList(), this, favoriteWordsRepository, sharedViewModel)
        recyclerView.adapter = favoriteWordsAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        sharedViewModel.selectedWord.observe(viewLifecycleOwner) {
            loadWords()
        }

        loadWords()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (textToSpeechRu?.isSpeaking == true){
            textToSpeechRu?.stop()
            textToSpeechRu?.shutdown()
        }
        if (textToSpeechEng?.isSpeaking == true){
            textToSpeechEng?.stop()
            textToSpeechEng?.shutdown()
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            textToSpeechRu?.setLanguage(Locale("ru"))
            textToSpeechEng?.setLanguage(Locale("en"))
        }
    }

    private fun loadWords() {
        CoroutineScope(Dispatchers.IO).launch {
            val words = favoriteWordsRepository.asyncLoadFavoriteWords()
            withContext(Dispatchers.Main) {
                favoriteWordsAdapter.updateItem(words)
            }
        }
    }

    override fun onClick(item: DictionaryEntity) {
        val dialogView = LayoutInflater.from(context)
            .inflate(R.layout.fullscreen_dictionary_dialog, requireView() as ViewGroup, false)

        val buttonOk = dialogView.findViewById<TextView>(R.id.okButton)
        val tajWordTextView = dialogView.findViewById<TextView>(R.id.tajWord)
        val rusWordTextView = dialogView.findViewById<TextView>(R.id.rusWord)
        val engWordTextView = dialogView.findViewById<TextView>(R.id.engWord)
        val transcriptionTextView = dialogView.findViewById<TextView>(R.id.transcription)

        tajWordTextView.text = item.wordTj
        rusWordTextView.text = item.wordRu
        engWordTextView.text = item.wordEng
        transcriptionTextView.text = item.transcription


        val alertDialog = AlertDialog.Builder(context).setView(dialogView).create()
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        ClickAreaButton.clickAreaButton(buttonOk)
        buttonOk.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()

        val imageViewPlayRu = dialogView.findViewById<ImageView>(R.id.imageViewPlayRu)
        val imageViewPlayEng = dialogView.findViewById<ImageView>(R.id.imageViewPlayEng)

        imageViewPlayRu.setOnClickListener {
            speakWordRu(item.wordRu)
        }

        imageViewPlayEng.setOnClickListener {
            speakWordEng(item.wordEng)
        }

    }

    private fun speakWordRu(word: String) {
        textToSpeechRu?.speak(word, TextToSpeech.QUEUE_FLUSH, null, null)
    }
    private fun speakWordEng(word: String) {
        textToSpeechEng?.speak(word, TextToSpeech.QUEUE_FLUSH, null, null)
    }
}
