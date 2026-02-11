package com.example.delefede

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.material.card.MaterialCardView

class SplashActivity : AppCompatActivity() {
    
    private var keepSplashOnScreen = true
    
    override fun onCreate(savedInstanceState: Bundle?) {
        // Instalar SplashScreen API
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { keepSplashOnScreen }
        
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_DeleFede)
        setContentView(R.layout.activity_splash)
        
        // Obtener vistas
        val logoCard = findViewById<MaterialCardView>(R.id.logoCard)
        val splashTitle = findViewById<TextView>(R.id.splashTitle)
        val splashSubtitle = findViewById<TextView>(R.id.splashSubtitle)
        val splashLocation = findViewById<TextView>(R.id.splashLocation)
        val loadingContainer = findViewById<LinearLayout>(R.id.loadingContainer)
        
        // Animación del logo (scale + fade)
        val logoScaleX = ObjectAnimator.ofFloat(logoCard, View.SCALE_X, 0.6f, 1f).apply {
            duration = 600
            interpolator = OvershootInterpolator(1.5f)
        }
        val logoScaleY = ObjectAnimator.ofFloat(logoCard, View.SCALE_Y, 0.6f, 1f).apply {
            duration = 600
            interpolator = OvershootInterpolator(1.5f)
        }
        val logoAlpha = ObjectAnimator.ofFloat(logoCard, View.ALPHA, 0f, 1f).apply {
            duration = 400
        }
        
        // Animación del título
        val titleFade = ObjectAnimator.ofFloat(splashTitle, View.ALPHA, 0f, 1f).apply {
            duration = 500
            startDelay = 300
        }
        val titleTranslate = ObjectAnimator.ofFloat(splashTitle, View.TRANSLATION_Y, 30f, 0f).apply {
            duration = 500
            startDelay = 300
            interpolator = AccelerateDecelerateInterpolator()
        }
        
        // Animación del subtítulo
        val subtitleFade = ObjectAnimator.ofFloat(splashSubtitle, View.ALPHA, 0f, 0.9f).apply {
            duration = 400
            startDelay = 500
        }
        val subtitleTranslate = ObjectAnimator.ofFloat(splashSubtitle, View.TRANSLATION_Y, 20f, 0f).apply {
            duration = 400
            startDelay = 500
            interpolator = AccelerateDecelerateInterpolator()
        }
        
        // Animación de la ubicación
        val locationFade = ObjectAnimator.ofFloat(splashLocation, View.ALPHA, 0f, 0.8f).apply {
            duration = 400
            startDelay = 600
        }
        
        // Animación del loading
        val loadingFade = ObjectAnimator.ofFloat(loadingContainer, View.ALPHA, 0f, 1f).apply {
            duration = 400
            startDelay = 700
        }
        
        // Ejecutar todas las animaciones
        AnimatorSet().apply {
            playTogether(
                logoScaleX, logoScaleY, logoAlpha,
                titleFade, titleTranslate,
                subtitleFade, subtitleTranslate,
                locationFade, loadingFade
            )
            start()
        }
        
        // Liberar splash screen nativa
        Handler(Looper.getMainLooper()).postDelayed({
            keepSplashOnScreen = false
        }, 100)
        
        // Navegar a MainActivity después de las animaciones
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }, 2200)
    }
}
