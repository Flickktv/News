package com.example.newsfetcher

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfetcher.feature.mainscreen.domain.ArticleModel

class ArticlesAdapter(
    var onItemClicked: (Int) -> Unit,
    var openItemClicked: (ArticleModel) -> Unit
) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {
    private var articlesData: List<ArticleModel> = emptyList()

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView
        val tvDate: TextView
        val ivStar: ImageView

        init {
            tvTitle = view.findViewById(R.id.tvTitle)
            tvDate = view.findViewById(R.id.tvDate)
            ivStar = view.findViewById(R.id.ivStar)
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_article, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.ivStar.setOnClickListener {
            onItemClicked.invoke(position)
        }

        viewHolder.itemView.setOnClickListener {
            openItemClicked.invoke(articlesData[position])
        }

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvTitle.text = articlesData[position].title
        viewHolder.tvDate.text = articlesData[position].publishedAt
        viewHolder.ivStar.visibility = ImageView.VISIBLE

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = articlesData.size

    fun setData(articles: List<ArticleModel>) {
        articlesData = articles
        notifyDataSetChanged()
    }

}
