package com.alif.newsapplication.model.dataSource.db.dictionary

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alif.newsapplication.model.dataSource.db.dictionary.dao.DictionaryDao
import com.alif.newsapplication.model.dataSource.db.dictionary.entity.DictionaryEntity

@Database(entities = [DictionaryEntity::class], version = 1)
abstract class DictionaryDataBase : RoomDatabase() {

    abstract fun dictionaryDao(): DictionaryDao

}