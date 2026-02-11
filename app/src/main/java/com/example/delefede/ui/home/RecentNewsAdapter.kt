package com.example.delefede.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.delefede.R
import com.google.android.material.card.MaterialCardView

class RecentNewsAdapter(
    private val news: List<NewsItem>,
    private val onItemClick: (NewsItem) -> Unit
) : RecyclerView.Adapter<RecentNewsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val card: MaterialCardView = itemView.findViewById(R.id.newsCard)
        private val titleText: TextView = itemView.findViewById(R.id.newsTitle)
        private val dateText: TextView = itemView.findViewById(R.id.newsDate)
        private val descText: TextView = itemView.findViewById(R.id.newsDescription)

        fun bind(item: NewsItem, position: Int) {
            titleText.text = item.title
            dateText.text = item.date
            descText.text = item.description

            card.setOnClickListener { view ->
                animateClick(view)
                onItemClick(item)
            }

            // Animación de entrada
            itemView.alpha = 0f
            itemView.translationX = 50f
            itemView.animate()
                .alpha(1f)
                .translationX(0f)
                .setDuration(400)
                .setStartDelay((position * 80).toLong())
                .setInterpolator(OvershootInterpolator(0.5f))
                .start()
        }

        private fun animateClick(view: View) {
            view.animate()
                .scaleX(0.96f)
                .scaleY(0.96f)
                .setDuration(100)
                .withEndAction {
                    view.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(150)
                        .setInterpolator(OvershootInterpolator(1.5f))
                        .start()
                }
                .start()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recent_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position], position)
    }

    override fun getItemCount(): Int = news.size
}