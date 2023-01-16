package com.example.newsfetcher.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://newsapi.org/"

const val API_KEY = "35df5fc13a9d40c48561b9892c3f38de"

val appModule = module {
    single <OkHttpClient> {
        OkHttpClient
            .Builder()
            .build()
    }

    single <Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
}