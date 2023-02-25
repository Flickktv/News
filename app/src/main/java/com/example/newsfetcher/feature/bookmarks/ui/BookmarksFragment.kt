package com.example.newsfetcher.feature.bookmarks.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfetcher.R
import com.example.newsfetcher.feature.domain.ArticleModel
import com.example.newsfetcher.feature.info.ui.FragmentDescribe
import com.example.newsfetcher.feature.mainscreen.ArticlesAdapter
import com.example.newsfetcher.feature.mainscreen.UiEvent
import com.example.newsfetcher.feature.mainscreen.MainViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {

    private val viewModel: BookmarksScreenViewModel by viewModel()
    private val recyclerView: RecyclerView by lazy { requireActivity().findViewById(R.id.rvBookmarkedArticles) }
    private val adapter: ArticlesAdapter by lazy {
        ArticlesAdapter(
            { index ->
                viewModel.processUiEvent(UiEvent.OnArticlesClicked(index))
            },
            { model ->openArticle(model) })
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        recyclerView.adapter = adapter
    }
    private fun render(viewState: FavoriteViewState) {
        adapter.setData(viewState.bookmarksArticle)

    }
    private fun openArticle(currentArticle: ArticleModel) {
        val bundle = Bundle()
        bundle.putParcelable("info", currentArticle)
        parentFragmentManager.beginTransaction().add(
            R.id.container, FragmentDescribe.newInstance(bundle)
        ).commit()

    }
}