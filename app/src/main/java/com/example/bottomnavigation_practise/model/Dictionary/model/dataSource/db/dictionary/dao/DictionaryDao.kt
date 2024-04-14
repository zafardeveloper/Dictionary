package com.alif.newsapplication.model.dataSource.db.dictionary.dao

import androidx.room.Dao
import androidx.room.Query
import com.alif.newsapplication.model.dataSource.db.dictionary.entity.DictionaryEntity

@Dao
interface DictionaryDao {

    @Query("SELECT * from data ORDER BY RANDOM() LIMIT 4")
    fun randomData(): List<DictionaryEntity>

    @Query("Select * from data where id = :id")
    fun wordById(id:Int): DictionaryEntity

}