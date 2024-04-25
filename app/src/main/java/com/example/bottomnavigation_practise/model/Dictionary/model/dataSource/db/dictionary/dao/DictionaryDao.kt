package com.alif.newsapplication.model.dataSource.db.dictionary.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.bottomnavigation_practise.model.Dictionary.model.dataSource.db.dictionary.entity.DictionaryEntity

@Dao
interface DictionaryDao {

    @Query("SELECT * from data ORDER BY RANDOM() LIMIT 4")
    fun randomData(): List<DictionaryEntity>

    @Query("Select * from data where id = :id")
    fun wordById(id:Int): DictionaryEntity

    @Query("Select * from data")
    fun readWords(): List<DictionaryEntity>

    @Query("SELECT * FROM data WHERE isFavorite = :isFavorite")
    fun readFavoriteWords(isFavorite: Int = 1): List<DictionaryEntity>

    @Update
    fun updateFavoriteStatus(entity: DictionaryEntity)

}