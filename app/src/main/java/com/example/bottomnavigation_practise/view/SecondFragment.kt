package com.example.bottomnavigation_practise.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation_practise.R
import com.example.bottomnavigation_practise.model.adapter.FavoriteWordsAdapter
import com.example.bottomnavigation_practise.model.adapter.ListAdapter
import com.example.bottomnavigation_practise.model.model.Word
import com.example.bottomnavigation_practise.vm.FavoriteWordsViewModel
class SecondFragment : Fragment(R.layout.fragment_second) {
    private lateinit var favoriteWordsViewModel: FavoriteWordsViewModel
    private val adapter = ListAdapter()
    private val recyclerView by lazy {
        requireView().findViewById<RecyclerView>(R.id.recyclerFavoriteView)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация ViewModel
        favoriteWordsViewModel = ViewModelProvider(requireActivity()).get(FavoriteWordsViewModel::class.java)

        // Настройка RecyclerView и адаптера
        recyclerView.adapter = adapter

        favoriteWordsViewModel.favoriteWords.observe(viewLifecycleOwner, { words ->
            adapter.updateItems(words)
            if (words.isNotEmpty()) {
                Toast.makeText(requireContext(), "Список избранных слов обновлен", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Список избранных слов пуст", Toast.LENGTH_SHORT).show()
            }
        })
    }
}