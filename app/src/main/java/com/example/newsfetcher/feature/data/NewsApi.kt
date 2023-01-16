package com.example.newsfetcher.feature.data

import com.example.newsfetcher.feature.data.model.ArticlesRemoteModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getArticles(
        @Query("apiKey") apiKey: String = "35df5fc13a9d40c48561b9892c3f38de",
        @Query("country") country: String = "ru",
    ) : ArticlesRemoteModel
}