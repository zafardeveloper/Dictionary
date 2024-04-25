package com.example.bottomnavigation_practise.model.Dictionary.model.dataSource.db.dictionary.entity

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
    @ColumnInfo(name = "transcrip")
    val transcription:String?,
    @ColumnInfo(name = "sound")
    val sound:String?,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Int = 0

){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}
