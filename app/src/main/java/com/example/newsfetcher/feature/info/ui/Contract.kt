package com.example.newsfetcher.feature.info.ui

import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.domain.ArticleModel

data class InfoViewState(
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,)

sealed class DataEvent() : Event {
    data class ShowInfo(
        val currentArticle: ArticleModel
    ) : DataEvent()
}