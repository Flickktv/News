package com.example.newsfetcher.feature.info.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.newsfetcher.R
import com.example.newsfetcher.feature.domain.ArticleModel
import com.example.newsfetcher.feature.mainscreen.MainScreenViewModel
import com.example.newsfetcher.feature.mainscreen.MainViewState
import com.google.android.material.appbar.CollapsingToolbarLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentDescribe : Fragment(R.layout.fragment_describe) {
   companion object{
       fun newInstance(arg: Bundle?): FragmentDescribe{
           val infoFragment = FragmentDescribe()
           infoFragment.arguments = arg
           return infoFragment
       }
   }

    val viewModel: InfoScreenViewModel by viewModel()
    val collapsingBar: CollapsingToolbarLayout by lazy { requireActivity().findViewById(R.id.newsCollapsingBar) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        val currentArticle: ArticleModel = this.arguments?.get("info") as ArticleModel
        viewModel.processUiEvent(DataEvent.ShowInfo(currentArticle))

    }
    private fun render(viewState: InfoViewState) {
      collapsingBar.title = viewState.title
    }
}