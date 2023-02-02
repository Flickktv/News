package com.example.newsfetcher.di

import androidx.room.Room
import com.example.newsfetcher.AppDataBase
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://newsapi.org/"

const val API_KEY = "35df5fc13a9d40c48561b9892c3f38de"
const val APP_DATABASE = "APP_DATABASE"

val networkModule = module {
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

val databaseModule = module {
    single {
        Room
            .databaseBuilder(androidContext(), AppDataBase::class.java, APP_DATABASE)
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<AppDataBase>().bookmarksDao()
    }
}