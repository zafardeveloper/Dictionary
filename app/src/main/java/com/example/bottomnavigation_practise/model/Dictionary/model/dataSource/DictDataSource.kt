package com.example.bottomnavigation_practise.model.Dictionary.model.dataSource

import com.example.bottomnavigation_practise.model.Dictionary.model.DictModel
import retrofit2.http.GET
import retrofit2.http.Query

interface DictDataSource {

    //https://newsapi.org/v2/48faf68bd8d243ee964a0421cc0caad5/news

    //https://newsapi.org/v2/everything?apiKey=48faf68bd8d243ee964a0421cc0caad5
    @GET("top-headlines")
    suspend fun getNewsEverything(
        @Query("sources") sources: String = "techcrunch"
    ): DictModel

}