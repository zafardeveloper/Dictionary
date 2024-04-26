package com.example.bottomnavigation_practise.model.Dictionary.model.dataSource.db.dictionary

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.alif.newsapplication.model.dataSource.db.dictionary.DictionaryDataBase
import kotlin.properties.Delegates.notNull

object DataBaseDataSource {
    var dictionaryDataBase: DictionaryDataBase by notNull()
    fun initDataBase(context: Context) {
        dictionaryDataBase =
            Room.databaseBuilder(context, DictionaryDataBase::class.java, "dictionary")
                .createFromAsset("dictionary.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}