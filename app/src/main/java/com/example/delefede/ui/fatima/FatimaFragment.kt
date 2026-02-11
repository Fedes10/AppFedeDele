package com.example.delefede.ui.fatima

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.delefede.R
import com.example.delefede.data.remote.GitHubApiService
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar

class FatimaFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fatima, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        loadFatimaImage(view)
        setupClickListeners(view)
        startEntryAnimations(view)
    }
    
    private fun loadFatimaImage(view: View) {
        val imageView = view.findViewById<ImageView>(R.id.fatimaBackground)
        val imageUrl = GitHubApiService.BASE_URL + "eventos/images/fatima-salida.jpg"
        
        Glide.with(this)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(imageView)
    }

    private fun setupClickListeners(view: View) {
        view.findViewById<View>(R.id.inscriptionButton)?.setOnClickListener {
            Snackbar.make(view, "Inscripciones próximamente disponibles", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun startEntryAnimations(view: View) {
        val infoCard = view.findViewById<MaterialCardView>(R.id.infoCard)
        val inscriptionCard = view.findViewById<MaterialCardView>(R.id.inscriptionCard)

        listOf(infoCard, inscriptionCard).forEachIndexed { index, card ->
            card?.let {
                it.alpha = 0f
                it.translationY = 40f
                
                AnimatorSet().apply {
                    playTogether(
                        ObjectAnimator.ofFloat(it, View.ALPHA, 0f, 1f),
                        ObjectAnimator.ofFloat(it, View.TRANSLATION_Y, 40f, 0f)
                    )
                    duration = 450
                    startDelay = (index * 100 + 200).toLong()
                    interpolator = DecelerateInterpolator()
                    start()
                }
            }
        }
    }
}
