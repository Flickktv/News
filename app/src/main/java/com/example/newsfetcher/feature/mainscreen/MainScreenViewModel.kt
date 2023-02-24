package com.example.newsfetcher.feature.mainscreen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.newsfetcher.base.BaseViewModel
import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.bookmarks.domain.BookmarksInteractor
import com.example.newsfetcher.feature.domain.ArticleModel
import com.example.newsfetcher.feature.domain.ArticlesInteractor
import kotlinx.coroutines.launch

class MainScreenViewModel(private val interactor: ArticlesInteractor, private val bookmarksInteractor: BookmarksInteractor) : BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.LoadArticles)
    }

    override fun initialViewState() = ViewState(
        articleModel = ArticleModel(null, null, null,null, null,null),
        articlesShown = emptyList(),
        articleList = emptyList(),
        isSearchEnabled = false
    )

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when(event) {
            is DataEvent.LoadArticles -> {
                viewModelScope.launch {
                    interactor.getArticles().fold(
                        onError = {
                            Log.e("ERROR", it.localizedMessage)
                        },
                        onSuccess = {
                            processDataEvent(DataEvent.OnLoadArticlesSucceed(it))
                        }
                    )
                }
                return null
            }
            is DataEvent.OnLoadArticlesSucceed -> {
                return previousState.copy(articleList = event.articles, articlesShown = event.articles)
            }
            is UiEvent.OnArticlesClicked -> {
                viewModelScope.launch {
                    bookmarksInteractor.create(previousState.articlesShown[event.index])
                }
                return null
            }

            is UiEvent.OpenArticle -> {
                previousState.articleModel = event.model
                return null
            }

            is UiEvent.OnSearchButtonClicked -> {
                return previousState.copy(articlesShown = if (previousState.isSearchEnabled) previousState.articleList else previousState.articlesShown,
                    isSearchEnabled = !previousState.isSearchEnabled)
            }

            is UiEvent.OnSearchEdit -> {
                return previousState.copy(articlesShown = previousState.articleList.filter {
                    it.title!!.contains(
                        event.text
                    )
                })
            }

            else -> return null
        }
    }
}