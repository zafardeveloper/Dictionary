package com.example.bottomnavigation_practise.dictionary_list

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation_practise.R
import com.example.bottomnavigation_practise.dictionary_list.adapter.ListAdapter
import com.example.bottomnavigation_practise.dictionary_list.model.ListModel
import java.util.Locale

class FirstFragment : Fragment(R.layout.fragment_first) {

    private val recyclerView by lazy {
        requireView()
            .findViewById<RecyclerView>(R.id.recyclerViewList)
    }
    private val adapter = ListAdapter()

    private val data = mutableListOf(
        ListModel("Калима", "слово"),
        ListModel("Китоб", "книга"),
        ListModel("Миз", "стол"),
        ListModel("Дарахт", "дерево"),
        ListModel("Хона", "дом"),
        ListModel("Мошин", "машина"),
        ListModel("Тиреза", "окно"),
        ListModel("Офтоб", "солнце"),
        ListModel("Баҳр", "море"),
        ListModel("Рег", "песок")
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