package com.example.bottomnavigation_practise.view

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation_practise.R
import com.example.bottomnavigation_practise.model.adapter.ListAdapter
import com.example.bottomnavigation_practise.model.model.Word
import com.example.bottomnavigation_practise.vm.FavoriteWordsViewModel

class FirstFragment : Fragment(R.layout.fragment_first) {

    private lateinit var favoriteWordsViewModel: FavoriteWordsViewModel

    private val recyclerView by lazy {
        requireView().findViewById<RecyclerView>(R.id.recyclerViewList)
    }
    private val adapter = ListAdapter()

    private val data = mutableListOf(
        Word("Калима", "слово"),
        Word("Китоб", "книга"),
        Word("Миз", "стол"),
        Word("Дарахт", "дерево"),
        Word("Хона", "дом"),
        Word("Мошин", "машина"),
        Word("Тиреза", "окно"),
        Word("Офтоб", "солнце"),
        Word("Баҳр", "море"),
        Word("Рег", "песок")
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        adapter.updateItems(data)
        view.findViewById<EditText>(R.id.searchEditText)?.apply {
            requestFocus()
            background = null
            doAfterTextChanged {
                filterChats(it)
            }
        }

        // Инициализация ViewModel
        favoriteWordsViewModel = ViewModelProvider(requireActivity()).get(FavoriteWordsViewModel::class.java)
        recyclerView.adapter = adapter
        // Установка слушателя для изменения состояния избранности
        adapter.setOnFavoriteCheckedChangeListener { words ->
            favoriteWordsViewModel.setFavoriteWords(words.filter { it.isFavorite
            })
            Toast.makeText(requireContext(), " добавлено в избранное", Toast.LENGTH_SHORT).show()
        }
    }





    private fun filterChats(query: Editable?) {
        query?.let { it ->
            if (it.isNotEmpty()) {
                val searchQuery = query.toString().lowercase()
                adapter.updateItems(data.filter {
                    it.tajWord.lowercase().contains(searchQuery) || it.rusWord.lowercase().contains(searchQuery)
                })
            } else adapter.updateItems(data)
        }
    }


}