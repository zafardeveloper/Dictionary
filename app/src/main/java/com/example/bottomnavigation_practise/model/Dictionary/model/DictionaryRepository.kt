package com.example.bottomnavigation_practise.model.Dictionary.model


import com.example.bottomnavigation_practise.model.Dictionary.model.dataSource.db.dictionary.entity.DictionaryEntity
//import com.example.bottomnavigation_practise.model.Dictionary.model.dataSource.db.history.DataBaseDataSource
import com.example.bottomnavigation_practise.view.victarina.vm.DictionaryResult
import com.example.bottomnavigation_practise.model.Dictionary.model.dataSource.db.dictionary.DataBaseDataSource
interface DictionaryRepository {

    suspend fun asyncLoadVictorinaWords(): DictionaryResult.VictorinaWordsModel

    suspend fun asyncLoadWordById(queryId: Int): DictionaryEntity




    class Base() : DictionaryRepository {

        private val dataBase = DataBaseDataSource.dictionaryDataBase.dictionaryDao()

        override suspend fun asyncLoadVictorinaWords(): DictionaryResult.VictorinaWordsModel {
            val result = dataBase.randomData()
            return DictionaryResult.VictorinaWordsModel(
                id = result.first().id,
                word = result.first().wordTj,
                answers = result.map { it.wordEng }.shuffled()
            )
        }

        override suspend fun asyncLoadWordById(queryId: Int): DictionaryEntity {
            return dataBase.wordById(queryId)
        }
    }
}