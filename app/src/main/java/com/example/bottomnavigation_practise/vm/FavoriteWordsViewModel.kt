package com.example.bottomnavigation_practise.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bottomnavigation_practise.model.Dictionary.model.ListModel


class FavoriteWordsViewModel : ViewModel() {

    val favoriteWords = MutableLiveData<List<ListModel>>()
    val favoriteWordsSet = MutableLiveData<Set<String>>()

    fun setFavoriteWords(words: List<ListModel>) {
        favoriteWords.value = words
        favoriteWordsSet.value = words.filter { it.isFavorite }.map { it.tajWord }.toSet()
    }

    fun addToFavorites(word: ListModel) {
        val currentFavorites = favoriteWords.value?.toMutableList() ?: mutableListOf()
        currentFavorites.add(word)
        setFavoriteWords(currentFavorites)
    }

    fun removeFromFavorites(word: ListModel) {
        val currentFavorites = favoriteWords.value?.toMutableList() ?: mutableListOf()
        currentFavorites.remove(word)
        setFavoriteWords(currentFavorites)
    }
}