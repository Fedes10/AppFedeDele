package com.example.delefede.ui.dele

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.example.delefede.R
import com.example.delefede.data.model.Cancion

class CancionAdapter(
    private var canciones: List<Cancion>,
    private val onCancionClick: (Cancion) -> Unit
) : RecyclerView.Adapter<CancionAdapter.CancionViewHolder>() {

    private var lastAnimatedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CancionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cancion, parent, false)
        return CancionViewHolder(view)
    }

    override fun onBindViewHolder(holder: CancionViewHolder, position: Int) {
        val cancion = canciones[position]
        holder.bind(cancion)
        animateItem(holder.itemView, position)
    }

    override fun getItemCount(): Int = canciones.size

    private fun animateItem(view: View, position: Int) {
        if (position > lastAnimatedPosition) {
            view.alpha = 0f
            view.translationY = 30f
            view.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(350)
                .setInterpolator(DecelerateInterpolator())
                .setStartDelay((position * 40).toLong().coerceAtMost(200))
                .start()
            lastAnimatedPosition = position
        }
    }

    fun updateList(newList: List<Cancion>) {
        canciones = newList
        notifyDataSetChanged()
    }

    fun resetAnimations() {
        lastAnimatedPosition = -1
    }

    inner class CancionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val card: MaterialCardView = itemView.findViewById(R.id.songCard)
        private val title: TextView = itemView.findViewById(R.id.songTitle)
        private val category: TextView = itemView.findViewById(R.id.songCategory)
        private val audioIndicator: ImageView = itemView.findViewById(R.id.audioIndicator)

        fun bind(cancion: Cancion) {
            title.text = cancion.titulo
            category.text = "${cancion.artista} • ${cancion.categoria}"
            
            // Mostrar indicador de audio si tiene URL de audio
            audioIndicator.visibility = if (cancion.audioUrl.isNotEmpty() || !cancion.audioLocal.isNullOrEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }
            
            // Click con animación
            card.setOnClickListener {
                it.animate()
                    .scaleX(0.97f)
                    .scaleY(0.97f)
                    .setDuration(80)
                    .withEndAction {
                        it.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(80)
                            .withEndAction {
                                onCancionClick(cancion)
                            }
                            .start()
                    }
                    .start()
            }
        }
    }
}
