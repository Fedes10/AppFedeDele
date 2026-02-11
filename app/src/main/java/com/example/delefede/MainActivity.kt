package com.example.delefede

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.delefede.ui.menu.MenuBottomSheetFragment

class MainActivity : AppCompatActivity() {
    
    private lateinit var navController: NavController
    private lateinit var fabCenter: FloatingActionButton
    private lateinit var scrimView: View
    private var isMenuOpen = false
    
    // Vistas de navegación
    private lateinit var navNewsContainer: LinearLayout
    private lateinit var navCalendarContainer: LinearLayout
    private lateinit var navCancioneroContainer: LinearLayout
    private lateinit var navProfileContainer: LinearLayout
    
    private lateinit var navNews: ImageView
    private lateinit var navCalendar: ImageView
    private lateinit var navCancionero: ImageView
    private lateinit var navProfile: ImageView
    
    private lateinit var navNewsText: TextView
    private lateinit var navCalendarText: TextView
    private lateinit var navCancioneroText: TextView
    private lateinit var navProfileText: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        setupBottomNavigation()
        setupNavigation()
        setupWindowInsets()
    }
    
    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        
        // Listener para actualizar el estado de navegación
        navController.addOnDestinationChangedListener { _, destination, _ ->
            updateNavigationState(destination.id)
        }
    }
    
    private fun setupBottomNavigation() {
        // FAB central
        fabCenter = findViewById(R.id.fabCenter)
        scrimView = findViewById(R.id.scrimView)
        
        // Contenedores de navegación
        navNewsContainer = findViewById(R.id.navNewsContainer)
        navCalendarContainer = findViewById(R.id.navCalendarContainer)
        navCancioneroContainer = findViewById(R.id.navCancioneroContainer)
        navProfileContainer = findViewById(R.id.navProfileContainer)
        
        // Iconos
        navNews = findViewById(R.id.navNews)
        navCalendar = findViewById(R.id.navCalendar)
        navCancionero = findViewById(R.id.navCancionero)
        navProfile = findViewById(R.id.navProfile)
        
        // Textos
        navNewsText = findViewById(R.id.navNewsText)
        navCalendarText = findViewById(R.id.navCalendarText)
        navCancioneroText = findViewById(R.id.navCancioneroText)
        navProfileText = findViewById(R.id.navProfileText)
        
        // Click listeners
        navNewsContainer.setOnClickListener {
            animateNavClick(it)
            navigateTo(R.id.noticiasFragment)
        }
        
        navCalendarContainer.setOnClickListener {
            animateNavClick(it)
            navigateTo(R.id.homeFragment)
        }
        
        navCancioneroContainer.setOnClickListener {
            animateNavClick(it)
            navigateTo(R.id.deleFragment)
        }
        
        navProfileContainer.setOnClickListener {
            animateNavClick(it)
            navigateTo(R.id.loginFragment)
        }
        
        // FAB central - Abre el menú
        fabCenter.setOnClickListener {
            animateFabClick()
            openMenu()
        }
        
        // Scrim click - Cierra el menú si está abierto
        scrimView.setOnClickListener {
            closeMenu()
        }
    }
    
    private fun navigateTo(destinationId: Int) {
        if (navController.currentDestination?.id != destinationId) {
            navController.navigate(destinationId)
        }
    }
    
    private fun updateNavigationState(currentDestinationId: Int) {
        val primaryColor = getColor(R.color.orange_primary)
        val secondaryColor = getColor(R.color.text_secondary)
        
        // Resetear todos los colores
        navNews.setColorFilter(secondaryColor)
        navCalendar.setColorFilter(secondaryColor)
        navCancionero.setColorFilter(secondaryColor)
        navProfile.setColorFilter(secondaryColor)
        
        navNewsText.setTextColor(secondaryColor)
        navCalendarText.setTextColor(secondaryColor)
        navCancioneroText.setTextColor(secondaryColor)
        navProfileText.setTextColor(secondaryColor)
        
        // Actualizar el seleccionado
        when (currentDestinationId) {
            R.id.noticiasFragment -> {
                navNews.setColorFilter(primaryColor)
                navNewsText.setTextColor(primaryColor)
            }
            R.id.homeFragment -> {
                navCalendar.setColorFilter(primaryColor)
                navCalendarText.setTextColor(primaryColor)
            }
            R.id.deleFragment -> {
                navCancionero.setColorFilter(primaryColor)
                navCancioneroText.setTextColor(primaryColor)
            }
            R.id.loginFragment -> {
                navProfile.setColorFilter(primaryColor)
                navProfileText.setTextColor(primaryColor)
            }
        }
    }
    
    private fun animateNavClick(view: View) {
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
    
    private fun animateFabClick() {
        val rotation = ObjectAnimator.ofFloat(fabCenter, View.ROTATION, 0f, 45f).apply {
            duration = 300
            interpolator = OvershootInterpolator(1.5f)
        }
        
        val scaleX = ObjectAnimator.ofFloat(fabCenter, View.SCALE_X, 1f, 1.1f, 1f).apply {
            duration = 300
        }
        
        val scaleY = ObjectAnimator.ofFloat(fabCenter, View.SCALE_Y, 1f, 1.1f, 1f).apply {
            duration = 300
        }
        
        AnimatorSet().apply {
            playTogether(rotation, scaleX, scaleY)
            start()
        }
    }
    
    private fun resetFabAnimation() {
        ObjectAnimator.ofFloat(fabCenter, View.ROTATION, 45f, 0f).apply {
            duration = 250
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }
    }
    
    private fun openMenu() {
        isMenuOpen = true
        showScrim()
        
        val menuBottomSheet = MenuBottomSheetFragment()
        menuBottomSheet.setOnDismissListener {
            isMenuOpen = false
            hideScrim()
            resetFabAnimation()
        }
        menuBottomSheet.show(supportFragmentManager, "MenuBottomSheet")
    }
    
    private fun closeMenu() {
        hideScrim()
        resetFabAnimation()
    }
    
    private fun showScrim() {
        scrimView.visibility = View.VISIBLE
        ObjectAnimator.ofFloat(scrimView, View.ALPHA, 0f, 1f).apply {
            duration = 250
            start()
        }
    }
    
    private fun hideScrim() {
        ObjectAnimator.ofFloat(scrimView, View.ALPHA, 1f, 0f).apply {
            duration = 200
            start()
        }
        scrimView.postDelayed({ scrimView.visibility = View.GONE }, 200)
    }
    
    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
    }
    
    override fun onBackPressed() {
        if (isMenuOpen) {
            closeMenu()
        } else {
            super.onBackPressed()
        }
    }
}