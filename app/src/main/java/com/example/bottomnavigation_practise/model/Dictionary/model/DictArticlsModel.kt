package com.example.bottomnavigation_practise.model.Dictionary.model


import com.google.gson.annotations.SerializedName

data class DictModel(
    @SerializedName("status")
    val status: String,
    @SerializedName("words")
    val words: List<DictArticlesModel>
)

data class DictArticlesModel(
    @SerializedName("word_tj")
    val word_tj: String = "",
    @SerializedName("word_ru")
    val word_ru: String = "",
    @SerializedName("word_eng")
    val word_eng: String,
    @SerializedName("transcription")
    val transcription: String = "",
    @SerializedName("sound")
    val sound: String = "",
    @SerializedName("sound")
    val isFavorite: Int

) {
    data class Source(
        @SerializedName("name")
        val name: String
    )
}