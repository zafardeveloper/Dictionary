package com.example.bottomnavigation_practise.view.favorite.vm

import com.alif.newsapplication.core.vm.BaseNewsViewModel

/*
b
sealed class NewsResult {
    data class NewArticle(val articles: List<NewsArticlesModel>) : NewsResult()

    data class IsNewsFavorite(val isFavorite: Boolean) : NewsResult()

}
class FavoriteFragmentViewModel : BaseNewsViewModel<NewsResult>() {

    private val newsFavoriteRepository = NewsFavoriteRepository.Base()

    fun loadFavorite() {
        launchIO {
            mutableResultLiveData.postValue(NewsResult.NewArticle(newsFavoriteRepository.asyncLoadFavorites()))
        }
    }

    fun removeFavorite(favorite: NewsArticlesModel) {
        launchIO {
            newsFavoriteRepository.asyncRemove(favorite)
            mutableResultLiveData.postValue(NewsResult.NewArticle(newsFavoriteRepository.asyncLoadFavorites()))
        }
    }

}*/
