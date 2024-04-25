package com.example.bottomnavigation_practise.view

import android.app.AlertDialog
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation_practise.R
import com.example.bottomnavigation_practise.model.Dictionary.model.DictionaryRepository
import com.example.bottomnavigation_practise.model.Dictionary.model.dataSource.db.dictionary.entity.DictionaryEntity
import com.example.bottomnavigation_practise.view.adapter.DictionaryAdapter
import com.example.bottomnavigation_practise.view.favorite.vm.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class HomeFragment :
    Fragment(R.layout.fragment_first),
    DictionaryAdapter.Listener,
    TextToSpeech.OnInitListener {

    private var textToSpeech: TextToSpeech? = null

    private val recyclerView by lazy {
        requireView().findViewById<RecyclerView>(R.id.recyclerViewList)
    }
    private val sharedViewModel: SharedViewModel by viewModels()

    private lateinit var dictionaryAdapter: DictionaryAdapter
    private val dictionaryRepository: DictionaryRepository = DictionaryRepository.Base()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        textToSpeech = TextToSpeech(requireContext(), this)

        dictionaryAdapter = DictionaryAdapter(emptyList(), this, dictionaryRepository, sharedViewModel)

        recyclerView.adapter = dictionaryAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        sharedViewModel.selectedWord.observe(viewLifecycleOwner) {
            loadWords()
        }

        loadWords()
        view.findViewById<EditText>(R.id.searchDictionaryEdText)?.apply {
            requestFocus()
            background = null
            doAfterTextChanged {
                filterChats(it)
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        textToSpeech?.stop()
        textToSpeech?.shutdown()
    }

    private fun loadWords() {
        CoroutineScope(Dispatchers.IO).launch {
            val words = dictionaryRepository.asyncLoadAllWords()
            withContext(Dispatchers.Main) {
                dictionaryAdapter.updateItem(words)
            }
        }
    }


    private fun filterChats(query: Editable?) {
        query?.let { it ->
            if (it.isNotEmpty()) {
                val searchQuery = query.toString().lowercase()

                CoroutineScope(Dispatchers.IO).launch {
                    val filteredWords = dictionaryRepository.asyncLoadAllWords().filter { word ->
                                word.wordTj.lowercase().contains(searchQuery) ||
                                word.wordRu.lowercase().contains(searchQuery) ||
                                word.wordEng.lowercase().contains(searchQuery)
                    }
                    withContext(Dispatchers.Main) {
                        dictionaryAdapter.updateItem(filteredWords)
                    }
                }

            } else {
                loadWords()
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

        buttonOk.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech?.setLanguage(Locale.ENGLISH)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Обработка ошибок инициализации TTS
            }
        } else {
            // Обработка ошибок инициализации TTS
        }
    }


}