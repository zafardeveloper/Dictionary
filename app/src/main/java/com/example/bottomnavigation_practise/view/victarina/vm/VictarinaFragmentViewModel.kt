package com.example.bottomnavigation_practise.view.victarina.vm

import com.alif.newsapplication.core.vm.BaseNewsViewModel
import com.example.bottomnavigation_practise.model.Dictionary.model.DictionaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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

    suspend fun checkAnswer(id: Int, answer: String): Boolean {
        return withContext(Dispatchers.IO) {
            val word = repository.asyncLoadWordById(id)
            word.wordEng == answer
        }
    }

}