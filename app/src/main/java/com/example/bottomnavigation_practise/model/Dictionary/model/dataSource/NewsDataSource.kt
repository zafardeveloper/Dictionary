package com.alif.newsapplication.model.dataSource

import com.alif.newsapplication.model.NewsModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NewsDataSource {

    //https://newsapi.org/v2/48faf68bd8d243ee964a0421cc0caad5/news

    //https://newsapi.org/v2/everything?apiKey=48faf68bd8d243ee964a0421cc0caad5
    @GET("top-headlines")
    suspend fun getNewsEverything(
        @Query("sources") sources: String = "techcrunch"
    ): NewsModel

}