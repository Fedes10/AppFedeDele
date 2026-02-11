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
import com.example.delefede.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        startEntryAnimations()
    }

    private fun setupClickListeners() {
        binding.registerButton.setOnClickListener {
            animateButton(it)
            performRegistration()
        }

        binding.loginLink.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun performRegistration() {
        val name = binding.nameEditText.text.toString().trim()
        val email = binding.emailEditText.text.toString().trim()
        val password = binding.passwordEditText.text.toString()
        val confirmPassword = binding.confirmPasswordEditText.text.toString()

        // Validaciones
        var hasErrors = false

        if (name.isEmpty()) {
            binding.nameLayout.error = "Introduce tu nombre"
            hasErrors = true
        } else {
            binding.nameLayout.error = null
        }

        if (email.isEmpty()) {
            binding.emailLayout.error = "Introduce tu correo electrónico"
            hasErrors = true
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailLayout.error = "Correo electrónico no válido"
            hasErrors = true
        } else {
            binding.emailLayout.error = null
        }

        if (password.isEmpty()) {
            binding.passwordLayout.error = "Introduce una contraseña"
            hasErrors = true
        } else if (password.length < 6) {
            binding.passwordLayout.error = "Mínimo 6 caracteres"
            hasErrors = true
        } else {
            binding.passwordLayout.error = null
        }

        if (confirmPassword != password) {
            binding.confirmPasswordLayout.error = "Las contraseñas no coinciden"
            hasErrors = true
        } else {
            binding.confirmPasswordLayout.error = null
        }

        if (!binding.termsCheckbox.isChecked) {
            Toast.makeText(context, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
            hasErrors = true
        }

        if (hasErrors) return

        // Mostrar loading
        showLoading(true)

        // Simular registro (aquí irá la lógica real)
        binding.root.postDelayed({
            showLoading(false)
            Toast.makeText(context, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show()
            // Volver al login
            findNavController().navigateUp()
        }, 1500)
    }

    private fun showLoading(show: Boolean) {
        binding.loadingOverlay.visibility = if (show) View.VISIBLE else View.GONE
        binding.registerButton.isEnabled = !show
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

        // Header fade in
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
        binding.registerCard.apply {
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
