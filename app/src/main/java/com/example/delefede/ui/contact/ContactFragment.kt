package com.example.delefede.ui.contact

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import com.example.delefede.databinding.FragmentContactBinding

class ContactFragment : Fragment() {

    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        startEntryAnimations()
    }

    private fun setupClickListeners() {
        // Abrir mapa para la dirección
        binding.addressCard.setOnClickListener {
            animateCard(it)
            val gmmIntentUri = Uri.parse("geo:0,0?q=Calle+Juan+de+Mena+3,+14002+Córdoba,+España")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            if (mapIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(mapIntent)
            } else {
                // Abrir en navegador si no hay Google Maps
                val browserIntent = Intent(Intent.ACTION_VIEW, 
                    Uri.parse("https://maps.google.com/?q=Calle+Juan+de+Mena+3,+14002+Córdoba,+España"))
                startActivity(browserIntent)
            }
        }

        // Llamar por teléfono
        binding.phoneCard.setOnClickListener {
            animateCard(it)
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:636886593")
            }
            startActivity(intent)
        }

        // Enviar email
        binding.emailCard.setOnClickListener {
            animateCard(it)
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:delejuventudcordoba@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT, "Contacto desde la app Dele Juventud")
            }
            startActivity(intent)
        }

        // Instagram
        binding.instagramCard.setOnClickListener {
            animateCard(it)
            openSocialMedia("https://instagram.com/delejuventudcordoba")
        }

        // Facebook
        binding.facebookCard.setOnClickListener {
            animateCard(it)
            openSocialMedia("https://facebook.com/delejuventudcordoba")
        }

        // YouTube
        binding.youtubeCard.setOnClickListener {
            animateCard(it)
            openSocialMedia("https://youtube.com/@delejuventudcordoba")
        }
    }

    private fun openSocialMedia(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun animateCard(view: View) {
        val scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.95f, 1f)
        val scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.95f, 1f)
        AnimatorSet().apply {
            playTogether(scaleX, scaleY)
            duration = 150
            start()
        }
    }

    private fun startEntryAnimations() {
        val cards = listOf(
            binding.iconCard,
            binding.addressCard,
            binding.phoneCard,
            binding.emailCard,
            binding.instagramCard,
            binding.facebookCard,
            binding.youtubeCard
        )

        cards.forEachIndexed { index, view ->
            view.alpha = 0f
            view.translationY = 50f

            view.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(400)
                .setStartDelay(index * 80L)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
