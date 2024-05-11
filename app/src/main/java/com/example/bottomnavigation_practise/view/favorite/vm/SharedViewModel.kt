package com.example.bottomnavigation_practise.view.favorite.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bottomnavigation_practise.model.Dictionary.model.dataSource.db.dictionary.entity.DictionaryEntity

class SharedViewModel: ViewModel() {
    val selectedWord = MutableLiveData<DictionaryEntity>()
}