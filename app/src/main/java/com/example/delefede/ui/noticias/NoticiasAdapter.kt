package com.example.delefede.ui.noticias

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.example.delefede.R

class NoticiasAdapter(
    private val onNoticiaClick: (Noticia) -> Unit
) : ListAdapter<Noticia, NoticiasAdapter.NoticiaViewHolder>(NoticiaDiffCallback()) {

    private var lastAnimatedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_noticia, parent, false)
        return NoticiaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticiaViewHolder, position: Int) {
        holder.bind(getItem(position))
        animateItem(holder.itemView, position)
    }

    private fun animateItem(view: View, position: Int) {
        if (position > lastAnimatedPosition) {
            view.alpha = 0f
            view.translationY = 50f
            view.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(400)
                .setInterpolator(DecelerateInterpolator())
                .setStartDelay((position * 50).toLong())
                .start()
            lastAnimatedPosition = position
        }
    }

    fun resetAnimations() {
        lastAnimatedPosition = -1
    }

    inner class NoticiaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val card: MaterialCardView = itemView.findViewById(R.id.noticiaCard)
        private val image: ImageView = itemView.findViewById(R.id.noticiaImage)
        private val categoryText: TextView = itemView.findViewById(R.id.noticiaCategory)
        private val featuredIcon: ImageView = itemView.findViewById(R.id.featuredIcon)
        private val dateText: TextView = itemView.findViewById(R.id.noticiaDate)
        private val titleText: TextView = itemView.findViewById(R.id.noticiaTitle)
        private val descriptionText: TextView = itemView.findViewById(R.id.noticiaDescription)

        fun bind(noticia: Noticia) {
            titleText.text = noticia.title
            descriptionText.text = noticia.description
            dateText.text = noticia.date
            categoryText.text = noticia.category.uppercase()
            
            // Imagen
            image.setImageResource(noticia.imageRes)
            
            // Featured indicator
            featuredIcon.visibility = if (noticia.isFeatured) View.VISIBLE else View.GONE
            
            // Click listener
            card.setOnClickListener {
                // Animación de click
                it.animate()
                    .scaleX(0.98f)
                    .scaleY(0.98f)
                    .setDuration(100)
                    .withEndAction {
                        it.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(100)
                            .withEndAction {
                                onNoticiaClick(noticia)
                            }
                            .start()
                    }
                    .start()
            }
        }
    }

    class NoticiaDiffCallback : DiffUtil.ItemCallback<Noticia>() {
        override fun areItemsTheSame(oldItem: Noticia, newItem: Noticia): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Noticia, newItem: Noticia): Boolean {
            return oldItem == newItem
        }
    }
}
