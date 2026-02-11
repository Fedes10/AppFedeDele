package com.example.delefede.ui.about

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.delefede.DeleFedeApp
import com.example.delefede.databinding.FragmentAboutBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!
    
    private val settingsDataStore by lazy { 
        (requireActivity().application as DeleFedeApp).settingsDataStore
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDarkModeSwitch()
        startEntryAnimations()
        setupCardAnimations()
    }
    
    private fun setupDarkModeSwitch() {
        // Observar el estado actual
        viewLifecycleOwner.lifecycleScope.launch {
            val isDarkMode = settingsDataStore.darkModeFlow.first()
            binding.darkModeSwitch.isChecked = isDarkMode
        }
        
        // Manejar cambios
        binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewLifecycleOwner.lifecycleScope.launch {
                settingsDataStore.setDarkMode(isChecked)
                
                // Aplicar tema con transición suave
                val mode = if (isChecked) {
                    AppCompatDelegate.MODE_NIGHT_YES
                } else {
                    AppCompatDelegate.MODE_NIGHT_NO
                }
                AppCompatDelegate.setDefaultNightMode(mode)
            }
        }
    }

    private fun setupCardAnimations() {
        val cards = listOf(
            binding.bishopCard,
            binding.delegate1Card,
            binding.delegate2Card,
            binding.deaconCard,
            binding.missionCard,
            binding.settingsCard
        )

        cards.forEach { card ->
            card.setOnClickListener {
                animateCard(it)
            }
        }
    }

    private fun animateCard(view: View) {
        val scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.97f, 1f)
        val scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.97f, 1f)
        AnimatorSet().apply {
            playTogether(scaleX, scaleY)
            duration = 200
            start()
        }
    }

    private fun startEntryAnimations() {
        // Animación del logo con bounce
        binding.appLogo.apply {
            alpha = 0f
            scaleX = 0.5f
            scaleY = 0.5f
            animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(500)
                .setInterpolator(OvershootInterpolator(1.5f))
                .start()
        }

        // Cards con efecto cascada
        val cards = listOf(
            binding.bishopCard,
            binding.delegate1Card,
            binding.delegate2Card,
            binding.deaconCard,
            binding.missionCard,
            binding.settingsCard
        )

        cards.forEachIndexed { index, view ->
            view.alpha = 0f
            view.translationX = -100f

            view.animate()
                .alpha(1f)
                .translationX(0f)
                .setDuration(400)
                .setStartDelay(200 + index * 100L)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
