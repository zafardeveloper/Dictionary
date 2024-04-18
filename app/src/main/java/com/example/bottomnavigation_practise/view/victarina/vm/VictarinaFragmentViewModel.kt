package com.example.bottomnavigation_practise.view.victarina.vm

import com.alif.newsapplication.core.vm.BaseNewsViewModel
import com.example.bottomnavigation_practise.model.Dictionary.model.DictionaryRepository

sealed class DictionaryResult {

    data class VictorinaWordsModel(
        val id: Int,
        val word: String,
        val answers: List<String>
    ) : DictionaryResult()

    data class VictorinaCheckModel(
        val isAnswerTrue: Boolean
    ) : DictionaryResult()

}

class DictionaryFragmentViewModel : BaseNewsViewModel<DictionaryResult>() {

    private val repository = DictionaryRepository.Base()

    fun loadRandomVictorina() {
        launchIO {
            mutableResultLiveData.postValue(repository.asyncLoadVictorinaWords())
        }
    }

    fun checkAnswer(id: Int, answer: String) {
        launchIO {
            val word = repository.asyncLoadWordById(id)
            mutableResultLiveData.postValue(
                DictionaryResult.VictorinaCheckModel(
                    word.wordEng == answer
                )
            )
        }
    }

}