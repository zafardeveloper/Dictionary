package com.alif.newsapplication.model.dataSource.db.dictionary.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data")
data class DictionaryEntity(
    @ColumnInfo(name = "word_tj")
    val wordTj: String,
    @ColumnInfo(name = "word_ru")
    val wordRu: String,
    @ColumnInfo(name = "word_eng")
    val wordEng: String,
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}
