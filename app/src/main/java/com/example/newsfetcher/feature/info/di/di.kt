package com.example.newsfetcher.feature.info.di

import com.example.newsfetcher.feature.info.ui.InfoScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val infoModel = module {
    viewModel{ InfoScreenViewModel()}
}
