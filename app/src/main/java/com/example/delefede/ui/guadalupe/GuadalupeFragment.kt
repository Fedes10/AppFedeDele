package com.example.delefede.ui.guadalupe

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import com.example.delefede.R
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar

class GuadalupeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_guadalupe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupClickListeners(view)
        startEntryAnimations(view)
    }

    private fun setupClickListeners(view: View) {
        view.findViewById<View>(R.id.inscriptionButton)?.setOnClickListener {
            Snackbar.make(view, "Inscripciones próximamente disponibles", Snackbar.LENGTH_SHORT).show()
        }
        
        view.findViewById<View>(R.id.viewAllGallery)?.setOnClickListener {
            Snackbar.make(view, "Galería próximamente disponible", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun startEntryAnimations(view: View) {
        val infoCard = view.findViewById<MaterialCardView>(R.id.infoCard)
        val galleryCard = view.findViewById<MaterialCardView>(R.id.galleryCard)
        val inscriptionCard = view.findViewById<MaterialCardView>(R.id.inscriptionCard)

        listOf(infoCard, galleryCard, inscriptionCard).forEachIndexed { index, card ->
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
