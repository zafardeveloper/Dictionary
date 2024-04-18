package com.example.bottomnavigation_practise.view

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation_practise.R
import com.example.bottomnavigation_practise.model.Dictionary.model.ListModel
import com.example.bottomnavigation_practise.view.adapter.ListAdapter

class HomeFragment : Fragment(R.layout.fragment_first), ListAdapter.Listener {


    private val recyclerView by lazy {
        requireView().findViewById<RecyclerView>(R.id.recyclerViewList)
    }
    private val adapter = ListAdapter(this)

    private val data = mutableListOf(
        ListModel("Калима", "слово", "Word", "[wɜːrd]"),
        ListModel("Китоб", "книга", "Book", "[bʊk]"),
        ListModel("Миз", "стол", "Table", "[ˈteɪbəl]"),
        ListModel("Дарахт", "дерево", "Tree", "[triː]"),
        ListModel("Хона", "дом", "House", "[haʊs]"),
        ListModel("Мошин", "машина", "Car", "[kɑːr]"),
        ListModel("Тиреза", "окно", "Window", "[ˈwɪndəʊ]"),
        ListModel("Офтоб", "солнце", "Sun", "[sʌn]"),
        ListModel("Баҳр", "море", "Sea", "[siː]"),
        ListModel("Рег", "песок", "Sand", "[sænd]"),
        ListModel("Ман талабаи синфи … мактаби миёна ҳастам.", "Я ученик .....класса средней школы.", "I am a pupil of the … form of the secondary school.", "[ˈaɪ ˌeɪˈɛm eɪ ˈpjuːpɪl ˈɒv ðə… ˈfɔːrm ˈɒv ðə ˈsɛkəndəri skuːl]")
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
                    it.tajWord.lowercase().contains(searchQuery) ||
                            it.rusWord.lowercase().contains(searchQuery) ||
                            it.engWord.lowercase().contains(searchQuery)

                })
            } else adapter.updateItems(data)
        }
    }

    override fun onClick(item: ListModel) {
        val dialogView = LayoutInflater.from(context)
            .inflate(R.layout.fullscreen_dictionary_dialog, requireView() as ViewGroup, false)

        val buttonOk = dialogView.findViewById<TextView>(R.id.okButton)
        val tajWordTextView = dialogView.findViewById<TextView>(R.id.tajWord)
        val rusWordTextView = dialogView.findViewById<TextView>(R.id.rusWord)
        val engWordTextView = dialogView.findViewById<TextView>(R.id.engWord)
        val transcriptionTextView = dialogView.findViewById<TextView>(R.id.transcription)

        tajWordTextView.text = item.tajWord
        rusWordTextView.text = item.rusWord
        engWordTextView.text = item.engWord
        transcriptionTextView.text = item.transcription


        val alertDialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .create()
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        buttonOk.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }




}