package com.example.newsfetcher.feature.mainscreen.data

import com.example.newsfetcher.feature.mainscreen.data.model.ArticleRemoteModel
import com.example.newsfetcher.feature.mainscreen.domain.ArticleModel
import kotlinx.coroutines.awaitAll

fun ArticleRemoteModel.toDomain() = ArticleModel(
    title = title ?: "",
    author = author ?: "",
    description = description ?: "",
    url = url ?: "",
    urlToImage = urlToImage ?: "",
    publishedAt = publishedAt ?: ""
)