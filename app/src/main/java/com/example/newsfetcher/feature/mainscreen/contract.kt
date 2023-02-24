package com.example.newsfetcher.feature.mainscreen

import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.domain.ArticleModel

data class ViewState(
    var articleModel: ArticleModel,
    val isSearchEnabled: Boolean,
    val articlesShown: List<ArticleModel>,
    var articleList: List<ArticleModel>
)

sealed class UiEvent : Event {
    data class OpenArticle(val model: ArticleModel) : UiEvent()
    data class OnArticlesClicked(val index : Int) : UiEvent()
    object OnSearchButtonClicked : UiEvent()
    data class OnSearchEdit(val text: String) : UiEvent()
}

sealed class DataEvent: Event {
    object LoadArticles : DataEvent()
    data class OnLoadArticlesSucceed(val articles: List<ArticleModel>) : DataEvent()
}