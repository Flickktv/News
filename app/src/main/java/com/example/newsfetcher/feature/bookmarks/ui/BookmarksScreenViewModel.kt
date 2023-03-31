package com.example.newsfetcher.feature.bookmarks.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.newsfetcher.base.BaseViewModel
import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.bookmarks.domain.BookmarksInteractor
import com.example.newsfetcher.feature.mainscreen.ui.UiEvent
import kotlinx.coroutines.launch

class BookmarksScreenViewModel(private val interactor: BookmarksInteractor) : BaseViewModel<FavoriteViewState>() {

    init {
        processDataEvent(DataEvent.LoadBookmarks)
    }
    override fun initialViewState(): FavoriteViewState = FavoriteViewState(bookmarksArticle = emptyList())

    override suspend fun reduce(event: Event, previousState: FavoriteViewState): FavoriteViewState? {
        when(event) {
            is DataEvent.LoadBookmarks -> {
                viewModelScope.launch {
                    interactor.read().fold(
                        onError = {},
                        onSuccess = {
                            processDataEvent(DataEvent.OnSuccessBookmarksLoaded(it))
                        }
                    )
                }
                return null
            }
            is DataEvent.OnSuccessBookmarksLoaded -> {
                Log.d("Room", "articleBookmark = ${event.bookmarksArticle}")
                return previousState.copy(bookmarksArticle = event.bookmarksArticle)
            }
            is UiEvent.OnArticlesClicked -> {
                previousState.bookmarksArticle[event.index].favoriteFlag = false
                viewModelScope.launch {
                    interactor.delete(previousState.bookmarksArticle[event.index])
                }
                return null
            }
            else -> return null
        }
    }
}