package com.example.delefede.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.delefede.R
import com.example.delefede.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        startEntryAnimations()
    }

    private fun setupClickListeners() {
        binding.loginButton.setOnClickListener {
            animateButton(it)
            performLogin()
        }

        binding.registerLink.setOnClickListener {
            // Navegar a registro (si existe)
            try {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            } catch (e: Exception) {
                Toast.makeText(context, "Registro próximamente disponible", Toast.LENGTH_SHORT).show()
            }
        }

        binding.forgotPasswordText.setOnClickListener {
            Toast.makeText(context, "Recuperación de contraseña próximamente", Toast.LENGTH_SHORT).show()
        }

        binding.googleButton.setOnClickListener {
            animateButton(it)
            Toast.makeText(context, "Login con Google próximamente", Toast.LENGTH_SHORT).show()
        }

        binding.appleButton.setOnClickListener {
            animateButton(it)
            Toast.makeText(context, "Login con Apple próximamente", Toast.LENGTH_SHORT).show()
        }
    }

    private fun performLogin() {
        val email = binding.emailEditText.text.toString().trim()
        val password = binding.passwordEditText.text.toString()

        // Validaciones básicas
        if (email.isEmpty()) {
            binding.emailLayout.error = "Introduce tu correo electrónico"
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailLayout.error = "Correo electrónico no válido"
            return
        }

        binding.emailLayout.error = null

        if (password.isEmpty()) {
            binding.passwordLayout.error = "Introduce tu contraseña"
            return
        }

        if (password.length < 6) {
            binding.passwordLayout.error = "La contraseña debe tener al menos 6 caracteres"
            return
        }

        binding.passwordLayout.error = null

        // Mostrar loading
        showLoading(true)

        // Simular login (aquí irá la lógica real de autenticación)
        binding.root.postDelayed({
            showLoading(false)
            Toast.makeText(context, "Login exitoso", Toast.LENGTH_SHORT).show()
            // Navegar a Home después del login
            try {
                findNavController().navigate(R.id.homeFragment)
            } catch (e: Exception) {
                // Ya en home o navegación no disponible
            }
        }, 1500)
    }

    private fun showLoading(show: Boolean) {
        binding.loadingOverlay.visibility = if (show) View.VISIBLE else View.GONE
        binding.loginButton.isEnabled = !show
        binding.emailEditText.isEnabled = !show
        binding.passwordEditText.isEnabled = !show
    }

    private fun animateButton(view: View) {
        val scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.95f, 1f)
        val scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.95f, 1f)
        AnimatorSet().apply {
            playTogether(scaleX, scaleY)
            duration = 150
            start()
        }
    }

    private fun startEntryAnimations() {
        // Logo con bounce
        binding.logoCard.apply {
            alpha = 0f
            scaleX = 0.5f
            scaleY = 0.5f
            animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(600)
                .setInterpolator(OvershootInterpolator(1.2f))
                .start()
        }

        // Header section fade in
        binding.headerSection.apply {
            alpha = 0f
            translationY = -30f
            animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(500)
                .setStartDelay(200)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }

        // Card slide up
        binding.loginCard.apply {
            alpha = 0f
            translationY = 100f
            animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(500)
                .setStartDelay(300)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }

        // Register link fade in
        binding.registerLink.apply {
            alpha = 0f
            animate()
                .alpha(1f)
                .setDuration(400)
                .setStartDelay(500)
                .start()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
