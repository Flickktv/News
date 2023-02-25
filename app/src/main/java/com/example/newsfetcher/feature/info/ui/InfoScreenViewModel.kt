package com.example.newsfetcher.feature.info.ui

import com.example.newsfetcher.base.BaseViewModel
import com.example.newsfetcher.base.Event

class InfoScreenViewModel () : BaseViewModel<InfoViewState>() {
    override fun initialViewState(): InfoViewState = InfoViewState(
         author = "",
         title= "",
         description= "",
         url= "",
         urlToImage= "",
         publishedAt= "",
    )

    override suspend fun reduce(event: Event, previousState: InfoViewState): InfoViewState? {
       when(event){
           is DataEvent.ShowInfo -> {
               return  previousState.copy(
                   author = event.currentArticle.author,
                   title= event.currentArticle.title,
                   description= event.currentArticle.description,
                   url= event.currentArticle.url,
                   urlToImage= event.currentArticle.urlToImage,
                   publishedAt= event.currentArticle.publishedAt,
               )
           }

       }
        return null
    }

}