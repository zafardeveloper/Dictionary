package com.example.bottomnavigation_practise.view.favorite.view

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation_practise.R
import com.example.bottomnavigation_practise.model.Dictionary.model.DictionaryRepository
import com.example.bottomnavigation_practise.model.Dictionary.model.dataSource.db.dictionary.entity.DictionaryEntity
import com.example.bottomnavigation_practise.view.adapter.DictionaryAdapter
import com.example.bottomnavigation_practise.view.adapter.FavoriteWordsAdapter
import com.example.bottomnavigation_practise.view.favorite.vm.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteFragment :
    Fragment(R.layout.fragment_second),
    DictionaryAdapter.Listener {

    private val recyclerView by lazy {
        requireView().findViewById<RecyclerView>(R.id.recyclerViewFavorite)
    }
    private val sharedViewModel: SharedViewModel by viewModels()

    private lateinit var favoriteWordsAdapter: FavoriteWordsAdapter
    private val favoriteWordsRepository: DictionaryRepository = DictionaryRepository.Base()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteWordsAdapter = FavoriteWordsAdapter(emptyList(), this, favoriteWordsRepository, sharedViewModel)
        recyclerView.adapter = favoriteWordsAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        sharedViewModel.selectedWord.observe(viewLifecycleOwner) {
            loadWords()
        }

        loadWords()
        view.findViewById<EditText>(R.id.searchFavoriteEdText)?.apply {
            requestFocus()
            background = null
            doAfterTextChanged {
                filterChats(it)
            }
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

    private fun filterChats(query: Editable?) {
        query?.let { it ->
            if (it.isNotEmpty()) {
                val searchQuery = query.toString().lowercase()

                CoroutineScope(Dispatchers.IO).launch {
                    val filteredWords = favoriteWordsRepository.asyncLoadAllWords().filter { word ->
                        word.wordTj.lowercase().contains(searchQuery) ||
                                word.wordRu.lowercase().contains(searchQuery) ||
                                word.wordEng.lowercase().contains(searchQuery)
                    }
                    withContext(Dispatchers.Main) {
                        favoriteWordsAdapter.updateItem(filteredWords)
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
}