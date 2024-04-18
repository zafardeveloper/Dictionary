package com.example.bottomnavigation_practise.model.Dictionary.model.dataSource.db.dictionary

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alif.newsapplication.model.dataSource.db.dictionary.dao.DictionaryDao
import com.example.bottomnavigation_practise.model.Dictionary.model.dataSource.db.dictionary.entity.DictionaryEntity

@Database(
    entities = [DictionaryEntity::class],
    version = 2,
)
abstract class NewHistoryDataBase : RoomDatabase() {

    abstract fun ditionaryDao(): DictionaryDao

    //tract fun favoriteDao(): NewsFavoriteArticlesDao

}