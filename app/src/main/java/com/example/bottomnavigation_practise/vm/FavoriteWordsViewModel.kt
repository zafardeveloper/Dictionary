package com.example.bottomnavigation_practise.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bottomnavigation_practise.model.Dictionary.model.Word


class FavoriteWordsViewModel : ViewModel() {
    private val _favoriteWords = MutableLiveData<List<Word>>()
    val favoriteWords: LiveData<List<Word>> = _favoriteWords

    // Метод для установки списка избранных слов
    fun setFavoriteWords(words: List<Word>) {
        _favoriteWords.value = words
    }

}