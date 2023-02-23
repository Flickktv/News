package com.example.newsfetcher.feature.mainscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.newsfetcher.R
import com.example.newsfetcher.feature.data.model.ArticleRemoteModel
import com.example.newsfetcher.feature.domain.ArticleModel
import com.google.android.material.appbar.CollapsingToolbarLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentDescribe : Fragment(R.layout.fragment_describe) {
    val viewModel: MainScreenViewModel by viewModel()
    val collapsingBar: CollapsingToolbarLayout by lazy { requireActivity().findViewById(R.id.newsCollapsingBar) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, ::render)

    }
    private fun render(viewState: ViewState) {
        collapsingBar.title =
    }
}