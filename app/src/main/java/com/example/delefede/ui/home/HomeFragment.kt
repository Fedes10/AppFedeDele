package com.example.delefede.ui.home

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delefede.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import java.util.Calendar

class HomeFragment : Fragment() {
    
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var recentNewsAdapter: RecentNewsAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupGreeting(view)
        setupQuickAccess(view)
        setupRecentNews(view)
        setupEventCards(view)
        startEntryAnimations(view)
    }
    
    private fun setupGreeting(view: View) {
        val greetingText = view.findViewById<TextView>(R.id.greetingText)
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        
        greetingText.text = when {
            hour < 12 -> "¡Buenos días!"
            hour < 20 -> "¡Buenas tardes!"
            else -> "¡Buenas noches!"
        }
    }
    
    private fun setupQuickAccess(view: View) {
        val quickCancionero = view.findViewById<LinearLayout>(R.id.quickCancionero)
        val quickGuadalupe = view.findViewById<LinearLayout>(R.id.quickGuadalupe)
        val quickNoticias = view.findViewById<LinearLayout>(R.id.quickNoticias)
        val quickContacto = view.findViewById<LinearLayout>(R.id.quickContacto)
        
        quickCancionero.setOnClickListener {
            animateClick(it)
            findNavController().navigate(R.id.deleFragment)
        }
        
        quickGuadalupe.setOnClickListener {
            animateClick(it)
            findNavController().navigate(R.id.guadalupeFragment)
        }
        
        quickNoticias.setOnClickListener {
            animateClick(it)
            findNavController().navigate(R.id.noticiasFragment)
        }
        
        quickContacto.setOnClickListener {
            animateClick(it)
            findNavController().navigate(R.id.contactFragment)
        }
        
        // Ver todas las noticias
        view.findViewById<TextView>(R.id.verTodasNoticias)?.setOnClickListener {
            findNavController().navigate(R.id.noticiasFragment)
        }
    }
    
    private fun setupRecentNews(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recentNewsRecycler)
        
        // Noticias de ejemplo
        val recentNews = listOf(
            NewsItem(
                "Vigilia de la Inmaculada 2024", 
                "6 Dic 2024",
                "Celebración especial en la catedral"
            ),
            NewsItem(
                "Guadalupe XXIX - Fotogalería", 
                "15 Nov 2024",
                "Revive los mejores momentos"
            ),
            NewsItem(
                "Proceso Guadalupe 2025", 
                "10 Nov 2024",
                "Inscripciones abiertas"
            ),
            NewsItem(
                "Convivencia de voluntarios", 
                "5 Nov 2024",
                "Encuentro mensual"
            )
        )
        
        recentNewsAdapter = RecentNewsAdapter(recentNews) { newsItem ->
            // Navegar a detalle de noticia
            findNavController().navigate(R.id.noticiasFragment)
        }
        
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = recentNewsAdapter
            clipToPadding = false
            setPadding(0, 0, 48, 0)
        }
    }
    
    private fun setupEventCards(view: View) {
        // Card de Iglesia Joven
        view.findViewById<MaterialCardView>(R.id.iglesiaJovenCard)?.setOnClickListener {
            animateCardClick(it)
            findNavController().navigate(R.id.noticiasFragment)
        }
        
        view.findViewById<MaterialButton>(R.id.btnVerEventos)?.setOnClickListener {
            findNavController().navigate(R.id.noticiasFragment)
        }
        
        // Peregrinaciones
        view.findViewById<MaterialCardView>(R.id.guadalupeCard)?.setOnClickListener {
            animateCardClick(it)
            findNavController().navigate(R.id.guadalupeFragment)
        }
        
        view.findViewById<MaterialCardView>(R.id.fatimaCard)?.setOnClickListener {
            animateCardClick(it)
            findNavController().navigate(R.id.fatimaFragment)
        }
    }
    
    private fun startEntryAnimations(view: View) {
        // Animar header
        val logoImage = view.findViewById<View>(R.id.logoImage)
        val greetingContainer = view.findViewById<View>(R.id.greetingContainer)
        val quoteCard = view.findViewById<View>(R.id.quoteCard)
        val quickAccessCard = view.findViewById<View>(R.id.quickAccessCard)
        
        // Animación del logo
        logoImage?.apply {
            alpha = 0f
            scaleX = 0.8f
            scaleY = 0.8f
            animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(500)
                .setInterpolator(OvershootInterpolator(1.5f))
                .start()
        }
        
        // Animación del saludo con efecto de máquina de escribir
        greetingContainer?.apply {
            alpha = 0f
            translationX = 30f
            animate()
                .alpha(1f)
                .translationX(0f)
                .setDuration(600)
                .setStartDelay(150)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()
        }
        
        // Animación del título de bienvenida con scale
        val welcomeTitle = view.findViewById<TextView>(R.id.welcomeTitle)
        welcomeTitle?.apply {
            scaleX = 0.9f
            scaleY = 0.9f
            animate()
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(500)
                .setStartDelay(350)
                .setInterpolator(OvershootInterpolator(1.2f))
                .start()
        }
        
        // Animación de la cita
        quoteCard?.apply {
            alpha = 0f
            translationY = 20f
            animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(500)
                .setStartDelay(300)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()
        }
        
        // Animación de accesos rápidos
        quickAccessCard?.apply {
            alpha = 0f
            translationY = 40f
            animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(600)
                .setStartDelay(400)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()
        }
    }
    
    private fun animateClick(view: View) {
        val scaleDown = AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 0.9f),
                ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 0.9f)
            )
            duration = 100
        }
        
        val scaleUp = AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(view, View.SCALE_X, 0.9f, 1f),
                ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.9f, 1f)
            )
            duration = 150
            interpolator = OvershootInterpolator(2f)
        }
        
        AnimatorSet().apply {
            playSequentially(scaleDown, scaleUp)
            start()
        }
    }
    
    private fun animateCardClick(view: View) {
        view.animate()
            .scaleX(0.97f)
            .scaleY(0.97f)
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

// Modelo de datos para noticias
data class NewsItem(
    val title: String,
    val date: String,
    val description: String
)
